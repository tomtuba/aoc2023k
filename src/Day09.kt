fun main() {
    fun lastValue(ls: List<Int>): Int {
        if (ls.all{ it == 0}) return 0
        val nextList = ls.indices.filter{ it > 0}.map{ ls[it] - ls[it-1]}
        return ls.last() + lastValue(nextList)
    }
    fun firstValue(ls: List<Int>): Int {
        if (ls.all{ it == 0}) return 0
        val nextList = ls.indices.filter{ it > 0}.map{ ls[it] - ls[it-1]}
        return ls.first() - firstValue(nextList)
    }
    fun part1(input: List<String>): Int {
        return input.sumOf{ line -> lastValue(line.split(" ").map{it.toInt()})}
    }

    fun part2(input: List<String>): Int {
        return input.sumOf{ line -> firstValue(line.split(" ").map{it.toInt()})}
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 114)
    check(part2(testInput) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
