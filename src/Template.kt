import kotlin.system.measureTimeMillis

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun runSolution(block: (List<String>) -> Int, file: String) {
        runCatching {
            readInput(file)
        }.onFailure {
            println("$file not found")
        }.onSuccess { input ->
            var result: Int
            val time = measureTimeMillis {
                result = block(input)
            }
            println("$file: $result (${time}ms)")
        }
    }

    val day = "XX"
    runSolution(::part1, "Day${day}part1")
    runSolution(::part2, "Day${day}part2")
}

