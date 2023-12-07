import java.lang.Integer.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var tally = 0
        input.forEachIndexed { numIndex, line ->
            line.indices.forEach { lineIndex ->
                if (line[lineIndex].isDigit() && (lineIndex == 0 || !line[lineIndex - 1].isDigit())) {
                    var end = line.substring(lineIndex).indexOfFirst{ !it.isDigit() } + lineIndex
                    if (end < lineIndex) end = line.length

                    if ((max(0, lineIndex - 1) until min(line.length, end + 1)).any { x ->
                            (max(0, numIndex - 1) until min(input.size, numIndex + 2))
                                .filter { y -> y != numIndex || x < lineIndex || x >= end }
                                .any { yIndex -> !input[yIndex][x].isDigit() && input[yIndex][x] != '.' }
                        }) {
                        tally += line.substring(lineIndex, end).toInt()
                    }
                }
            }
        }
        return tally
    }

    fun getNumbersFromLine(line: String, place: Int): List<Int> {
        var start = place
        var end = place+1

        while (start > 0 && line[start-1].isDigit()) start -= 1
        while (end < line.length && line[end].isDigit()) end += 1

        return line.substring(start, end)
            .split(".","*","#","+","$")
            .filter{ it.isNotEmpty() }.map{ it.toInt() }
    }

    fun part2(input: List<String>): Int {
        return input.mapIndexed { inputIndex, line ->
            line.indices.filter{ line[it] == '*' }
                .map{
                    val numList = mutableListOf<Int>()

                    if (inputIndex > 0) numList.addAll(getNumbersFromLine(input[inputIndex-1], it))
                    numList.addAll(getNumbersFromLine(line, it))
                    if (inputIndex < input.size - 1) numList.addAll(getNumbersFromLine(input[inputIndex+1], it))
                    numList
                }
                .filter{ it.size == 2}
                .sumOf { it[0] * it[1] }
            }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
