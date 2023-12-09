fun main() {
    val cards = "AKQJT98765432"
    val ranks = "FEDCB98765432"
    val ranks2 = "FED1B98765432"

    val scores = listOf("11","21","22","31","32","41", "50")

    fun countCards(hand: String): List<Int> {
        return cards.map{ card -> hand.count { card == it }}.sortedDescending()
    }

    fun countCards2(hand: String): List<Int> {
        val jokers = hand.count{ it == 'J'}
        val remaining = cards.filter{it != 'J'}
            .map{ card -> hand.count { card == it }}.sortedDescending()
            .toMutableList()
        remaining[0] += jokers
        return remaining
    }

    fun handScore(hand: String, counts: List<Int>): Int {
        return scores.indexOf(counts.subList(0, 2).joinToString("") { it.toString() }) + 1
    }

    fun splitHand(line: String): List<String> {
        return line.split(" ")
    }

    fun convertHand(hand: String): String {
        return "" + handScore(hand, countCards(hand)) + hand.map{ ranks[cards.indexOf(it)]}
    }
    fun convertHand2(hand: String): String {
        return "" + handScore(hand, countCards2(hand)) + hand.map{ ranks2[cards.indexOf(it)]}
    }
    fun part1(input: List<String>): Long {
        val parts = input.map{ splitHand(it)}.map{ listOf(convertHand(it[0]),it[1]) }.sortedBy { it[0] }
        return parts.mapIndexed { index, strings -> strings[1].toLong() * (index+1) }.sum()
    }

    fun part2(input: List<String>): Long {
        val parts = input.map{ splitHand(it)}.map{ listOf(convertHand2(it[0]),it[1]) }.sortedBy { it[0] }
        return parts.mapIndexed { index, strings -> strings[1].toLong() * (index+1) }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440L)
    check(part2(testInput) == 5905L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
