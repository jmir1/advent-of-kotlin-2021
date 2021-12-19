fun main() {

    fun Char.getPendant(): Char {
        return when (this) {
            '<' -> '>'
            '>' -> '<'
            '(' -> ')'
            ')' -> '('
            '{' -> '}'
            '}' -> '{'
            '[' -> ']'
            ']' -> '['
            else -> '0'
        }
    }

    fun Char.isOpening(): Boolean {
        return when (this) {
            '<', '(', '{', '[' -> true
            else -> false
        }
    }

    fun Char.toCorruptionScore(): Int {
        return when (this) {
            '(', ')' -> 3
            '[', ']' -> 57
            '{', '}' -> 1197
            '<', '>' -> 25137
            else -> 0
        }
    }

    fun Char.toCompletionScore(): Long {
        return when (this) {
            '(', ')' -> 1L
            '[', ']' -> 2L
            '{', '}' -> 3L
            '<', '>' -> 4L
            else -> 0L
        }
    }

    fun part1(input: List<String>): Int {
        var score = 0
        input.forEach { line ->
            val stack = mutableListOf<Char>()
            var char = '0'
            for (c in line) {
                if (c.isOpening()) stack.add(c)
                else {
                    if (stack.last() == c.getPendant()) {
                        stack.removeLast()
                    } else {
                        char = c
                        break
                    }
                }
            }
            score += char.toCorruptionScore()
        }
        return score
    }

    fun part2(input: List<String>): Long {
        val corruptLines = mutableListOf<Int>()
        input.forEachIndexed { i, line ->
            val stack = mutableListOf<Char>()
            for (c in line) {
                if (c.isOpening()) stack.add(c)
                else {
                    if (stack.last() == c.getPendant()) {
                        stack.removeLast()
                    } else {
                        corruptLines.add(i)
                        break
                    }
                }
            }
        }
        val incompleteLines = input.filterIndexed { index, _ -> index !in corruptLines }
        val scores = mutableListOf<Long>()
        incompleteLines.forEach { line ->
            val stack = mutableListOf<Char>()
            for (c in line) {
                if (c.isOpening()) stack.add(c.getPendant())
                else {
                    if (stack.last() == c) {
                        stack.removeLast()
                    }
                }
            }
            var lineScore = 0L
            stack.reversed().forEach { c ->
                lineScore *= 5L
                lineScore += c.toCompletionScore()
            }
            scores.add(lineScore)
        }
        val sortedScores = scores.sorted()
        val middleIndex = sortedScores.size / 2
        return sortedScores[middleIndex]
    }

    val input = readInput("input-day10")
    println(part1(input))
    println(part2(input))
}
