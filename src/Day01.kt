fun main() {
    fun part1(input: List<String>): Int {
        var increases = 0
        for (i in 0 until input.size - 1) {
            val before = Integer.parseInt(input[i])
            val current = Integer.parseInt(input[i+1])
            if (before < current) increases++
        }
        return increases
    }

    fun part2(input: List<String>): Int {
        var increases = 0
        for (i in 0 until input.size - 3) {
            val a = Integer.parseInt(input[i])
            val b = Integer.parseInt(input[i+1])
            val c = Integer.parseInt(input[i+2])
            val d = Integer.parseInt(input[i+3])
            val before = a + b + c
            val current = b + c + d
            if (before < current) increases++
        }
        return increases
    }

    val input1 = readInput("Day01part1")
    val input2 = readInput("Day01part2")
    println(part1(input1))
    println(part2(input2))
}
