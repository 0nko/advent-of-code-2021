fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        input.forEach {
            val (move, dist) = it.split(" ")
            val distInt = Integer.parseInt(dist)
            when (move){
                "forward" -> x += distInt
                "down" -> y += distInt
                "up" -> y -= distInt
            }
        }
        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var aim = 0
        input.forEach {
            val (move, dist) = it.split(" ")
            val distInt = Integer.parseInt(dist)
            when (move){
                "forward" -> {
                    x += distInt
                    y += aim * distInt
                }
                "down" -> aim += distInt
                "up" -> aim -= distInt
            }
        }
        return x * y
    }

    val input1 = readInput("Day02part1")
    val input2 = readInput("Day02part2")
    println(part1(input1))
    println(part2(input2))
}
