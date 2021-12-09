import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)
data class Line(val start: Point, val end: Point) {
    fun isHorizontal() = start.y == end.y

    fun isVertical() = start.x == end.x

    fun isDiagonal(): Boolean {
        return !(isHorizontal() || isVertical())
    }

    /**
     * returns if the angle of a diagonal line is 45, 135, 225 or 315 degrees.
     */
    fun angle(): Int {
        if (!isDiagonal()) throw Exception("line isn't diagonal!")
        return when {
            start.x < end.x && start.y < end.y -> 45
            start.x < end.x && start.y > end.y -> 135
            start.x > end.x && start.y > end.y -> 225
            start.x > end.x && start.y < end.y -> 315
            else -> throw Exception("error getting angle")
        }
    }
}

fun main() {
    fun parseLines(input: List<String>): List<Line> {
        val lineList = mutableListOf<Line>()
        for (line in input) {
            val startX = line.substringBefore(",").toInt()
            val startY = line.substringAfter(",").substringBefore(" ->").toInt()
            val endX = line.substringAfter(" -> ").substringBefore(",").toInt()
            val endY = line.substringAfter(" -> ").substringAfter(",").toInt()
            lineList.add(Line(Point(startX, startY), Point(endX, endY)))
        }
        return lineList
    }

    fun part1(input: List<String>): Int {
        val lines = parseLines(input)
        val points = mutableListOf<Point>()
        for (line in lines) {
            when {
                line.isVertical() -> {
                    val startY = min(line.start.y, line.end.y)
                    val endY = max(line.start.y, line.end.y)
                    for (y in startY..endY) {
                        points.add(Point(line.start.x, y))
                    }
                }
                line.isHorizontal() -> {
                    val startX = min(line.start.x, line.end.x)
                    val endX = max(line.start.x, line.end.x)
                    for (x in startX..endX) {
                        points.add(Point(x, line.start.y))
                    }
                }
            }
        }

        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part2(input: List<String>): Int {
        val lines = parseLines(input)
        val points = mutableListOf<Point>()
        for (line in lines) {
            when {
                line.isVertical() -> {
                    val startY = min(line.start.y, line.end.y)
                    val endY = max(line.start.y, line.end.y)
                    for (y in startY..endY) {
                        points.add(Point(line.start.x, y))
                    }
                }
                line.isHorizontal() -> {
                    val startX = min(line.start.x, line.end.x)
                    val endX = max(line.start.x, line.end.x)
                    for (x in startX..endX) {
                        points.add(Point(x, line.start.y))
                    }
                }
                line.isDiagonal() -> {
                    // check line angle
                    when (line.angle()) {
                        45 -> {
                            for (x in line.start.x..line.end.x) {
                                val y = line.start.y + (x - line.start.x)
                                points.add(Point(x, y))
                            }
                        }
                        135 -> {
                            for (x in line.start.x..line.end.x) {
                                val y = line.start.y - (x - line.start.x)
                                points.add(Point(x, y))
                            }
                        }
                        225 -> {
                            for (x in line.start.x downTo line.end.x) {
                                val y = line.start.y - (line.start.x - x)
                                points.add(Point(x, y))
                            }
                        }
                        315 -> {
                            for (x in line.start.x downTo line.end.x) {
                                val y = line.start.y + (line.start.x - x)
                                points.add(Point(x, y))
                            }
                        }
                    }

                }
            }
        }

        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    val input = readInput("input-day05")
    println(part1(input))
    println(part2(input))
}
