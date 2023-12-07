
fun main() {

    fun lineMatchesValue(line: String, value: Long): Boolean {
        val arr = line.split(" ").map{it.toLong()}
        return (arr[1] until arr[1]+arr[2]).contains(value)
    }

    fun newValue(line: String, value: Long): Long {
        val arr = line.split(" ").map{it.toLong()}
        return arr[0] + value - arr[1]
    }

    fun newValue(step: List<Long>, value: Long): Long {
        return step[0] + value - step[1]
    }

    fun lineMatchesValue(step: List<Long>, value: Long): Boolean {
        return value >= step[1] && value < step[1] + step[2]
    }

    fun soilValue(steps: List<List<List<Long>>>, incomingSeed: Long): Long {
        var seed = incomingSeed
        steps.forEach{ step ->
            val matches = step.filter{ lineMatchesValue(it, seed)}
            seed = if (matches.isEmpty()) seed
            else newValue(matches[0], seed)
        }
        return seed
    }

    fun lowestSoilValue(input: List<String>, incomingSeeds: List<Long>): Long {
        val startLines = input.indices.filter{ input[it].isEmpty() }.map{ it + 2}

        var seeds = incomingSeeds.toList()

        startLines.forEachIndexed{ index, startLine ->
            val endLine = if (index == startLines.size-1) input.size-1 else startLines[index+1]-3

            seeds = seeds.map{seed ->
                val matches = (startLine..endLine).map{ input[it] }.filter{ lineMatchesValue(it, seed)}
                if (matches.isEmpty()) seed
                else newValue(matches[0], seed)
            }
        }
        return seeds.min()
    }

    fun part1(input: List<String>): Long {
        val seeds = input[0].substringAfter(": ").split(" ").map{ it.toLong()}

        return lowestSoilValue(input, seeds)
    }

    fun part2(input: List<String>): Long {
        val values = input[0].substringAfter(": ").split(" ").map{ it.toLong()}
        values.println()

        val startLines = input.indices.filter{ input[it].isEmpty() }.map{ it + 2}

        val steps = startLines.mapIndexed { index, startLine ->
            val endLine = if (index == startLines.size - 1) input.size else startLines[index + 1] - 2
            input.subList(startLine,endLine).map{ line -> line.split(" ").map{ it.toLong() } }
        }

        steps.println()

        var minValue = Long.MAX_VALUE
        var counter = 0L

        (values.indices step 2).forEach{ index ->
            "***** Index: $index".println()
            (values[index] until values[index]+values[index+1])
                .forEach {
                    counter += 1
                    if (counter % 10000000 == 0L) counter.println()
                    val soil = soilValue(steps, it)
                    if (soil < minValue) {
                        minValue = soil
                        soil.println()
                    }
                }
        }

        minValue.println()

        return minValue
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)
    check(part2(testInput) == 46L)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
