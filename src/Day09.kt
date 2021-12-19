fun main() {
    fun part1(input: List<String>): Int {
        val a = input.map { it.map { char -> char.digitToInt() }.toIntArray() }.toTypedArray()
        var sum = 0
        for (y in a.indices) {
            for (x in a[y].indices) {
                val adjacent = IntArray(4) { 10 }
                if (x > 0) adjacent[0] = a[y][x-1]
                if (y > 0) adjacent[1] = a[y-1][x]
                if (x < a[y].lastIndex) adjacent[2] = a[y][x+1]
                if (y < a.lastIndex) adjacent[3] = a[y+1][x]
                val min = adjacent.minOrNull() ?: 10
                if (min > a[y][x]) {
                    sum += 1 + a[y][x]
                }
            }
        }
        return sum
    }

    data class Coord(val y: Int, val x: Int)

    fun recursiveInefficientSearch(a: Array<IntArray>, coordSet: MutableSet<Coord>, startingPoint: Coord) {
        val y = startingPoint.y
        val x = startingPoint.x
        if (coordSet.contains(startingPoint)) return
        if (a[y][x] == 9) return
        coordSet.add(startingPoint)
        if (y > 0 && a[y-1][x] > a[y][x]) recursiveInefficientSearch(a, coordSet, Coord(y-1, x))
        if (x > 0 && a[y][x-1] > a[y][x]) recursiveInefficientSearch(a, coordSet, Coord(y, x-1))
        if (y < a.lastIndex && a[y+1][x] > a[y][x]) recursiveInefficientSearch(a, coordSet, Coord(y+1, x))
        if (x < a[y].lastIndex && a[y][x+1] > a[y][x]) recursiveInefficientSearch(a, coordSet, Coord(y, x+1))
    }

    fun part2(input: List<String>): Int {
        val a = input.map { it.map { char -> char.digitToInt() }.toIntArray() }.toTypedArray()
        val lowpoints = mutableListOf<Coord>()
        for (y in a.indices) {
            for (x in a[y].indices) {
                val adjacent = IntArray(4) { 10 }
                if (x > 0) adjacent[0] = a[y][x-1]
                if (y > 0) adjacent[1] = a[y-1][x]
                if (x < a[y].lastIndex) adjacent[2] = a[y][x+1]
                if (y < a.lastIndex) adjacent[3] = a[y+1][x]
                val min = adjacent.minOrNull() ?: 10
                if (min > a[y][x]) {
                    lowpoints.add(Coord(y, x))
                }
            }
        }
        val basins = mutableListOf<Int>()
        // i think this part is extremely inefficient
        lowpoints.forEach {
            val coordSet = mutableSetOf<Coord>()
            recursiveInefficientSearch(a, coordSet, it)
            basins.add(coordSet.size)
        }
        var result = 1
        basins.sortedDescending().take(3).forEach { result *= it }
        return result
    }

    val input = readInput("input-day09")
    println(part1(input))
    println(part2(input))
}
