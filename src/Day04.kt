import java.lang.Exception

data class Win(val number: Int, val board: Int)

fun main() {
    fun boardsFromInput(input: List<String>): List<Array<Array<Pair<Int, Boolean>>>> {
        val boardList = mutableListOf<Array<Array<Pair<Int, Boolean>>>>()
        var currentBoard = mutableListOf<Array<Pair<Int, Boolean>>>()
        for (line in input) {
            if (line.isEmpty()) {
                boardList.add(currentBoard.toTypedArray())
                currentBoard = mutableListOf()
                continue
            }
            currentBoard.add(line.trim().split("  ", " ").map { Pair(it.toInt(), false) }.toTypedArray())
        }
        return boardList
    }

    fun determineWinningNumberAndBoard(draws: List<Int>, boards: List<Array<Array<Pair<Int, Boolean>>>>): Win {
        for (draw in draws) {
            for (board in boards.indices) {
                for (line in 0..4) {
                    for (col in 0..4) {
                        if (boards[board][line][col].first == draw) {
                            boards[board][line][col] = Pair(boards[board][line][col].first, true)
                        }
                    }
                }
                val cols = mutableListOf<Array<Pair<Int, Boolean>>>()
                for (col in 0..4) {
                    val column = mutableListOf<Pair<Int, Boolean>>()
                    for (line in 0..4) {
                        column.add(boards[board][line][col])
                    }
                    cols.add(column.toTypedArray())
                }
                for (col in cols) {
                    if (col.filter { it.second }.size == 5) return Win(draw, board)
                }
                for (line in boards[board]) {
                    if (line.filter { it.second }.size == 5) return Win(draw, board)
                }
            }
        }
        throw Exception("nobody won :(")
    }

    fun part1(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = boardsFromInput(input.drop(2))
        val winningBoardAndNumber = determineWinningNumberAndBoard(draws, boards)
        val winningBoard = winningBoardAndNumber.board
        val winningNumber = winningBoardAndNumber.number
        var points = 0
        for (line in boards[winningBoard]) {
            for (num in line) {
                if (!num.second) points += num.first
            }
        }
        return points * winningNumber
    }

    fun determineLastWinningNumberAndBoard(draws: List<Int>, boards: List<Array<Array<Pair<Int, Boolean>>>>): Win {
        var lastWin: Win? = null
        val hasWon = mutableSetOf<Int>()
        for (draw in draws) {
            for (board in boards.indices) {
                if (board in hasWon) continue
                for (line in 0..4) {
                    for (col in 0..4) {
                        if (boards[board][line][col].first == draw) {
                            boards[board][line][col] = Pair(boards[board][line][col].first, true)
                        }
                    }
                }
                val cols = mutableListOf<Array<Pair<Int, Boolean>>>()
                for (col in 0..4) {
                    val column = mutableListOf<Pair<Int, Boolean>>()
                    for (line in 0..4) {
                        column.add(boards[board][line][col])
                    }
                    cols.add(column.toTypedArray())
                }
                for (col in cols) {
                    if (col.filter { it.second }.size == 5) {
                        lastWin = Win(draw, board)
                        hasWon.add(board)
                    }
                }
                for (line in boards[board]) {
                    if (line.filter { it.second }.size == 5) {
                        lastWin = Win(draw, board)
                        hasWon.add(board)
                    }
                }
            }
        }
        return lastWin ?: throw Exception("nobody won :(")
    }

    fun part2(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = boardsFromInput(input.drop(2))
        val lastWinningBoardAndNumber = determineLastWinningNumberAndBoard(draws, boards)
        val winningBoard = lastWinningBoardAndNumber.board
        val winningNumber = lastWinningBoardAndNumber.number
        var points = 0
        for (line in boards[winningBoard]) {
            for (num in line) {
                if (!num.second) points += num.first
            }
        }
        return points * winningNumber
    }

    val input = readInput("input-day04")
    println(part1(input))
    println(part2(input))
}
