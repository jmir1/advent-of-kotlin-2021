import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val crabPositions = input.first().split(",").map(String::toInt)
        val min = min(crabPositions)
        val max = max(crabPositions)
        val costs = mutableListOf<Int>()
        for (pos in min..max) {
            var cost = 0
            crabPositions.forEach {
                cost += abs(it - pos)
            }
            costs.add(cost)
        }

        return min(costs)
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input.first().split(",").map(String::toInt)
        val min = min(crabPositions)
        val max = max(crabPositions)
        val costs = mutableListOf<Int>()
        for (pos in min..max) {
            var cost = 0
            crabPositions.forEach {
                val distance = abs(it - pos)
                cost += (1..distance).sum()
            }
            costs.add(cost)
        }

        return min(costs)
    }

    val input = readInput("input-day07")
    println(part1(input))
    println(part2(input))
}
