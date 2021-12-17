import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach {
            val outputs = it.substringAfter("| ").split(" ")
            outputs.forEach { output ->
                when (output.length) {
                    2, 3, 4, 7 -> count++
                }
            }
        }
        return count
    }

    fun orderNumbers(jumbledNumbers: List<String>): List<String> {
        val orderedNumbers = mutableListOf<String>()
        jumbledNumbers.forEach {
            orderedNumbers.add(String(it.toCharArray().apply(CharArray::sort)))
        }
        return orderedNumbers
    }

    fun lettersToNumbers(letters: String): IntArray {
        return letters.map { it.code - 96 }.toIntArray().sortedArray()
    }

    fun numbersToLetters(numbers: IntArray): String {
        return String(numbers.map { (it + 96).toChar() }.toCharArray())
    }

    fun decodeNumbers(signal: List<String>): List<String> {
        val numbers = signal.map(::lettersToNumbers)
        val l = numbers.groupBy { it.size }
        val results = Array(10) { intArrayOf() }
        results[8] = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        results[1] = l[2]!!.first()
        results[7] = l[3]!!.first()
        results[4] = l[4]!!.first()
        results[3] = l[5]!!.first { it.toList().containsAll(results[7].toList()) }
        results[9] = l[6]!!.first { it.toList().containsAll(results[4].toList()) }
        results[6] = l[6]!!.first { !it.toList().containsAll(results[1].toList()) }
        results[0] = l[6]!!.first { !it.contentEquals(results[6]) && !it.contentEquals(results[9]) }
        results[5] = l[5]!!.filterNot { it.contentEquals(results[3]) }.first { results[9].toList().containsAll(it.toList()) }
        results[2] = l[5]!!.first { !it.contentEquals(results[3]) && !it.contentEquals(results[5]) }

        return results.map(::numbersToLetters)
    }

    fun part2(input: List<String>): Int {
        var tally = 0
        input.forEach {
            val signal = it.substringBefore(" |").split(" ")
            val output = it.substringAfter("| ").split(" ")
            val decodedNumbers = decodeNumbers(signal)
            assert(decodedNumbers.size == 10)
            val orderedOutput = orderNumbers(output)
            orderedOutput.reversed().forEachIndexed { i, o -> tally += (10.0.pow(i) * decodedNumbers.indexOfFirst { d -> o == d }).toInt() }
        }
        return tally
    }

    val input = readInput("input-day08")
    println(part1(input))
    println(part2(input))
}
