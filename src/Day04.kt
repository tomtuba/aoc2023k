import kotlin.math.pow

fun main() {
    fun countWinningNumbers(line: String): Int {
        val numbers = line.substringAfter(": ")
        val winners = numbers.substringBefore(" |").split(" ").filter{ it.isNotEmpty() }.toSet()
        return numbers.substringAfter("| ").trim().split(" ").filter{ it.isNotEmpty()}.count{ winners.contains(it)}
    }
    fun scoreLine(line: String): Int {
        val winningCount = countWinningNumbers(line)
        return if (winningCount == 0 ) 0 else 2.0.pow(winningCount - 1.0).toInt()
    }
    fun part1(input: List<String>): Int {
        return input.sumOf { scoreLine(it) }
    }

    fun part2(input: List<String>): Int {
        val countMap = input.mapIndexed { index, s -> index to countWinningNumbers(s) }.toMap()

        val countList = IntArray(input.size) {1}

        input.indices.forEach {inputIndex ->
            (inputIndex+1 until inputIndex +1 + countMap.getOrDefault(inputIndex,0))
                .filter { it < countList.size}
                .forEach { countList[it] += countList[inputIndex] }
            }

        return countList.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
