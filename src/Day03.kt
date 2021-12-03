fun main() {
    fun getBitSums(input: List<String>): List<Int> {
        var sums = List(input[0].length) { 0 }
        input.forEach { diag ->
            val ints = diag.map { Integer.parseInt(it.toString()) }
            sums = sums.zip(ints) { a, b -> a + b }
        }
        return sums
    }

    fun binary2Int(string: String): UInt = Integer.parseInt(string, 2).toUInt()

    fun part1(input: List<String>): UInt {
        var sums = getBitSums(input)
        var gammaString = ""
        var epsilonString = ""
        val n = input.size
        sums.forEach {
            if (it > n / 2) {
                gammaString += "1"
                epsilonString += "0"
            } else {
                gammaString += "0"
                epsilonString += "1"
            }
        }

        val gamma = binary2Int(gammaString)
        val epsilon = binary2Int(epsilonString)
        return gamma * epsilon
    }

    fun findLast(input: List<String>, position: Int, useMoreCommon: Boolean, preferredChar: Char): String {
        if (input.size == 1) {
            return input[0]
        }

        val sums = getBitSums(input)
        val half = input.size / 2
        val wanted = when {
            sums[position] == half -> preferredChar
            sums[position] > half -> if (useMoreCommon) '1' else '0'
            else -> if (useMoreCommon) '0' else '1'
        }
        return findLast(input.filter { it[position] == wanted }, position + 1, useMoreCommon, preferredChar)
    }

    fun part2(input: List<String>): UInt {
        val oxygenString = findLast(input, 0, true, '1')
        val co2String = findLast(input, 0, false, '0')
        val oxygen = binary2Int(oxygenString)
        val co2 = binary2Int(co2String)
        return oxygen * co2
    }

    val input1 = readInput("Day03part1")
    val input2 = readInput("Day03part2")
    println(part1(input1))
    println(part2(input2))
}
