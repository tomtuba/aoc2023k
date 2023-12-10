fun main() {
    // S N E W
    val dirs = listOf(listOf(0,1), listOf(0,-1), listOf(1,0), listOf(-1,0))
    val toNorth = "|LJS".map{it.toString()}.toSet()
    val toSouth = "|7FS".map{it.toString()}.toSet()
    val toEast = "-LFS".map{it.toString()}.toSet()
    val toWest = "-J7S".map{it.toString()}.toSet()

    fun findStart(input: List<String>): List<Int> {
        val y = input.indices.find{ input[it].contains("S") }!!
        val x = input[y].indexOf("S")
        return listOf(x,y)
    }

    fun buildVisited(input: List<String>): Set<List<Int>> {
        val startSpot = findStart(input)
        var spot = startSpot

        val visited = mutableSetOf<List<Int>>()

        while (true) {
            visited.add(spot)
            val spotChar = input[spot[1]][spot[0]].toString()
            val spots = dirs.map{ dir -> (0..1).map{dir[it]+spot[it]}}
                .filter{ nxt -> nxt[0] >= 0 && nxt[1]>= 0 && nxt[0] < input[0].length && nxt[1]<input.size}
                .filter{ nxt -> ! visited.contains(nxt)}
                .filter { nxt ->
                    val nxtChar = input[nxt[1]][nxt[0]].toString()

                    if (nxt[0] < spot[0] && toWest.contains(spotChar) && toEast.contains(nxtChar)) true
                    else if (nxt[0] > spot[0] && toEast.contains(spotChar) && toWest.contains(nxtChar)) true
                    else if (nxt[1] > spot[1] && toSouth.contains(spotChar) && toNorth.contains(nxtChar)) true
                    else if (nxt[1] < spot[1] && toNorth.contains(spotChar) && toSouth.contains(nxtChar)) true
                    else false
                }
            if (spots.isEmpty()) break
            spot = spots[0]
        }
        return visited
    }
    fun part1(input: List<String>): Int {
        return buildVisited(input).size/2
    }

    fun oddCrossings(chars: List<String>): Boolean {
        val counts = chars.groupingBy { it }.eachCount()

        return toNorth.sumOf{counts.getOrDefault(it,0) } % 2 == 1
    }

    fun part2(input: List<String>): Int {
        val visited = buildVisited(input)
        return input.indices.sumOf { y ->
            input[0].indices.filter{ ! visited.contains(listOf(it, y)) }
                .map{ x ->
                    val range = if (input[y].substring(0,x).contains("S")) (x+1 until input[0].length) else (0 until x)
                    val lineWest = range.filter{ visited.contains(listOf(it,y))}
                        .map{ input[y][it].toString()}
                    if (oddCrossings(lineWest)) 1
                    else 0
                }.sum()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 8)
    check(part2(readInput("Day10b_test")) == 10)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
