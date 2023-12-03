fun main() {
    fun gameSplit(line: String): List<Int> {
        val cubes = line.substring(line.indexOf(":")+2)
        val colorSections = cubes.split("; ").flatMap { it.split(", ") }

        return arrayOf("red", "green", "blue")
            .map { color ->
                colorSections.filter { it.contains(color) }.maxOfOrNull { it.substringBefore(" ").toInt() } ?: 0
            }
    }

    fun gameId(line: String): Int {
        return line.substring(5, line.indexOf(":")).toInt()
    }

    fun part1(input: List<String>): Int {
        return input.filter {
            val arr = gameSplit(it)
            arr[0] <= 12 && arr[1] <= 13 && arr[2] <= 14
        }.sumOf { gameId(it) }
    }

    fun part2(input: List<String>): Int {
        return input.map { gameSplit(it) }.sumOf { it.reduce{ acc, i -> acc * i } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
