fun main() {
    val numbers = arrayOf("zero","one","two","three","four","five","six","seven","eight","nine")
    fun part1(input: List<String>): Int {
        return input.map { it.filter { c -> c in '0'..'9' } }.sumOf { String(charArrayOf(it.first(), it.last())).toInt() }
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            line.indices.map {
                ndx ->
                if (line[ndx] in '0'..'9') {
                    (""+line[ndx]).toInt()
                } else {
                    (1 .. 9).find { line.indexOf(numbers[it], ndx) == ndx} ?: -1
                }
            }.filter { it > -1 }
        }.map { arr -> ("" + arr.first() + arr.last()).toInt() }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInput2 = readInput("Day01b_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
