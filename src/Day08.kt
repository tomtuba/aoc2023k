fun main() {
    val start = "AAA"
    val finish = "ZZZ"

    fun getMap(input: List<String>): Map<String, List<String>> {
        return input.subList(2,input.size).map{ line ->
            val key = line.substringBefore(" ")
            val entry = line.substring(7, 15).split(", ")
            key to entry
        }.toMap()
    }
    fun part1(input: List<String>): Int {
        val dirs = input[0];
        val lrMap = getMap(input)

        var location = start
        var turns = 0

        while (location != finish) {
            val turn = dirs[turns % dirs.length]
            val next = lrMap[location]!!
            location = next[if (turn == 'L') 0 else 1]
            turns += 1
        }
        return turns
    }

    fun countBetween(dirs: String, lrMap: Map<String, List<String>>, startSpot: String) {
        var oneTurn = startSpot

        var turns = 0L
        var dirIndex = 0

        while (! oneTurn.endsWith("Z") || turns == 0L) {
            val dir = dirs[dirIndex]
            dirIndex = (dirIndex + 1) % dirs.length
            oneTurn = lrMap[oneTurn]!![if (dir == 'L') 0 else 1]
            turns += 1
        }

        listOf(startSpot, oneTurn, turns, turns / dirs.length).println()
    }

    fun part2(input: List<String>): Long {
        val dirs = input[0];
        val lrMap = getMap(input)
        dirs.length.println()

        val starts = lrMap.keys.filter { it.endsWith("A") }.toList()

        starts.forEach { startSpot ->
            countBetween(dirs, lrMap, startSpot)
        }

        val ends = lrMap.keys.filter { it.endsWith("Z") }.toList()

        ends.forEach { startSpot ->
            countBetween(dirs, lrMap, startSpot)
        }
//        while (turn.any{ ! it.endsWith("Z")}) {
//            val dir = dirs[dirIndex]
//            dirIndex = (dirIndex + 1) % dirs.length
//            turn = turn.map{ lrMap[it]!![if (dir == 'L') 0 else 1]}
//            if (turns % 100000000 == 0L) turns.println()
//            turns += 1
//        }

        return 6L
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
//    check(part1(testInput) == 6)
//    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    //part1(input).println()
    part2(input).println()
}
