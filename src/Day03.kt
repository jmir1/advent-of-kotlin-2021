fun main() {
    fun part1(input: List<String>): Int {
        val list = mutableListOf<Int>()
        for (i in 0..11) {
            list.add(0)
            input.forEach {
                if (it[i] == '1') list[i]++
            }
        }
        val gamma = list.joinToString(separator = "") {
            if (it >= input.size / 2) "1"
            else "0"
        }
        val epsilon = gamma.toCharArray().joinToString(separator = "") {
            if (it == '0') "1"
            else "0"
        }
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {
        var oxygen = input
        for (i in 0..11) {
            if (oxygen.size == 1) break
            val zeroes = oxygen.filter { it[i] == '0' }
            val ones = oxygen.filter { it[i] == '1' }
            oxygen = if (ones.size >= zeroes.size) {
                ones
            } else {
                zeroes
            }
        }
        var co2 = input
        for (i in 0..11) {
            if (co2.size == 1) break
            val zeroes = co2.filter { it[i] == '0' }
            val ones = co2.filter { it[i] == '1' }
            co2 = if (zeroes.size <= ones.size) {
                zeroes
            } else {
                ones
            }
        }
        return oxygen[0].toInt(2) * co2[0].toInt(2)
    }

    val input = readInput("input-day03")
    println(part1(input))
    println(part2(input))
}
