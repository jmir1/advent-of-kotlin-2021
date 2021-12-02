fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var hPos = 0
        input.forEach {
            when {
                it.startsWith("forward") -> hPos += it.substringAfter("forward ").toInt()
                it.startsWith("down") -> depth += it.substringAfter("down ").toInt()
                it.startsWith("up") -> depth -= it.substringAfter("up ").toInt()
            }
        }
        return depth * hPos
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var hPos = 0
        var aim = 0
        input.forEach {
            when {
                it.startsWith("forward") -> {
                    val x = it.substringAfter("forward ").toInt()
                    hPos += x
                    depth += aim * x
                }
                it.startsWith("down") -> aim += it.substringAfter("down ").toInt()
                it.startsWith("up") -> aim -= it.substringAfter("up ").toInt()
            }
        }
        return depth * hPos
    }

    val input = readInput("input-day02")
    println(part1(input))
    println(part2(input))
}
