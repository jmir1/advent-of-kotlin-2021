fun main() {
    fun part1(input: List<String>): Int {
        val fishList = input.first().split(",").mapTo(mutableListOf()) { it.toInt() }
        for (day in 1..80) {
            val newFishes = mutableListOf<Int>()
            fishList.forEachIndexed { index, fish ->
                when (fish) {
                    0 ->  {
                        fishList[index] = 6
                        newFishes.add(8)
                    }
                    else -> fishList[index] -= 1
                }
            }
            fishList.addAll(newFishes)
        }
        return fishList.size
    }

    fun part2(input: List<String>): Long {
        val fishList = input.first().split(",").map { it.toInt() }
        var fishArray = Array(9) { 0L }
        fishList.forEach {
            fishArray[it]++
        }
        for (day in 1..256) {
            val newArray = Array(9) { 0L }
            for (age in 8 downTo 0) {
                if (age == 0) {
                    newArray[8] = fishArray[0]
                    newArray[6] += fishArray[0]
                } else {
                    newArray[age - 1] = fishArray[age]
                }
            }
            fishArray = newArray

        }
        return fishArray.sum()
    }

    val input = readInput("input-day06")
    println(part1(input))
    println(part2(input))
}
