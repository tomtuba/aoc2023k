fun main() {
    fun splitLine(line: String): List<Long> {
        return line.substringAfter(": ").split(" ").filter{ it.isNotEmpty()}.map{it.toLong()}
    }
    fun makeRaces(input: List<String>): List<List<Long>> {
        val times = splitLine(input[0])
        val distances = splitLine(input[1])

        return times.indices.map{ listOf(times[it], distances[it]) }
    }

    fun successfulTimes(race: List<Long>): Long {
        return (0 .. race[0]).count{it * (race[0]-it) > race[1]}.toLong()
    }

    fun part1(input: List<String>): Long {
       return makeRaces(input).map{successfulTimes(it)}.reduce{ a,b -> a * b}
    }

    fun part2(input: List<String>): Long {
        val race = input.map{ line -> splitLine(line).joinToString("") { it.toString() } }.map{ it.toLong()}
       return successfulTimes(race)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288L)
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
