import kotlin.math.max

class HIndex {
    fun solution(citations: IntArray): Int {
        var answer = 0
        citations.sortDescending()

        var hIndex = citations[citations.lastIndex]

        while (hIndex <= citations[0]) {
            if (citations.count { it >= hIndex } >= hIndex) {
                answer = hIndex
            }
            hIndex += 1
        }
        return answer
    }
}

fun main() {
    HIndex().solution(intArrayOf(1,1,1,1,1,1)).also { println(it) }
}