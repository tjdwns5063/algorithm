import kotlin.math.min

fun cntEqual(lottos: IntArray, win_nums: IntArray): Int {
    var cnt = 0

    lottos.forEachIndexed { index, i ->
        if (win_nums.contains(lottos[index]))
            ++cnt
    }
    return cnt
}

fun checkRank(cnt: Int): Int {
    return when (cnt) {
        6 -> 1
        5 -> 2
        4 -> 3
        3 -> 4
        2 -> 5
        else -> 6
    }
}

fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
    var answer: IntArray = intArrayOf()
    val maxRank = checkRank(cntEqual(lottos, win_nums) + lottos.count { it == 0 })
    val minRank = checkRank(cntEqual(lottos, win_nums))
    answer += maxRank
    answer += minRank

    return answer
}

fun main() {
    println(solution(intArrayOf(44, 1, 0, 0, 31, 25), intArrayOf(31, 10, 45, 1, 6, 19)).toList())
}