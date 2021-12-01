fun main() {
    fun part1(input: List<String>): Int {
        val intList = input.map { it.toInt() }
        var last = intList.first()
        var result = 0
        intList.forEach {
            if (it > last) result++
            last = it
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val intList = input.map { it.toInt() }
        var last = intList[0] + intList[1] + intList[2]
        var result = 0
        intList.forEachIndexed { index, it ->
            if (index > 2) {
                val sum = intList[index - 2] + intList[index - 1] + it
                if (sum > last) result++
                last = sum
            }
        }
        return result
    }

    val input = readInput("input-day01")
    println(part1(input))
    println(part2(input))
}
