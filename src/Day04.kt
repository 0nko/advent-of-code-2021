import kotlin.system.measureTimeMillis

typealias Board = MutableList<Pair<Int, Boolean>>

fun main() {
    fun List<Board>.markNumber(number: Int) {
        forEach { board ->
            val index = board.indexOfFirst { it.first == number }
            if (index >= 0) {
                board[index] = Pair(0, true)
            }
        }
    }

    fun Board.hasBingo(): Boolean {
        // rows
        for (i in 0..4) {
            if (slice(i*5 .. i*5+4).all { it.second }) return true
        }
        // cols
        for (i in 0..4) {
            if (slice(i until size step 5).all { it.second }) return true
        }
        return false
    }

    fun Board.getScore(lastNumber: Int) = sumOf { it.first } * lastNumber

    fun List<String>.createBoard(): Board {
        val board = mutableListOf<Pair<Int, Boolean>>()
        forEach { inputLine ->
            val line = inputLine
                    .split(" ")
                    .filter { it.isNotEmpty() }
                    .map { Pair(Integer.parseInt(it), false) }
            board.addAll(line)
        }
        return board
    }

    fun List<String>.createBoards(): List<Board> {
        var i = 2
        val boards = mutableListOf<Board>()
        while (i < size) {
            val boardInput = this.slice(i..i+4)
            boards.add(boardInput.createBoard())
            i += 6
        }
        return boards
    }

    fun part1(input: List<String>): Int {
        var bestScore = -1
        val numbers = input[0].split(",").map { Integer.parseInt(it) }
        val boards = input.createBoards()

        numbers.forEach { num ->
            boards.markNumber(num)
            boards.filter { it.hasBingo() }.maxOfOrNull { it.getScore(num) }?.let {
                bestScore = it
            }
            if (bestScore != -1) {
                return bestScore
            }
        }
        return bestScore
    }

    fun part2(input: List<String>): Int {
        var score = -1
        val numbers = input[0].split(",").map { Integer.parseInt(it) }
        var boards = input.createBoards()

        numbers.forEach { num ->
            boards.markNumber(num)
            if (boards.size == 1) {
                val board = boards.first()
                if (board.hasBingo()) {
                    return board.getScore(num)
                }
            } else {
                boards = boards.filter { !it.hasBingo() }
            }
        }
        return score
    }

    fun runSolution(block: (List<String>) -> Int, file: String) {
        val input = readInput(file)
        var result: Int
        val time = measureTimeMillis {
            result = block(input)
        }
        println("$file: $result (${time}ms)")
    }

    runSolution(::part1, "Day04part1")
    runSolution(::part2, "Day04part2")
}

