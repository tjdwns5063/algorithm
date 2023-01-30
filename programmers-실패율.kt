class FailureRate {
    fun solution(N: Int, stages: IntArray): IntArray {
        var record = Array(N) { Array(2) { 0 } }
        var answer = IntArray(N) { it + 1 }

        for (stage in stages) {
            if (stage == N + 1) {
                for (i in record.indices) {
                    record[i][0] += 1
                    record[i][1] += 1
                }
            } else {
                for (i in 0 until stage) {
                    record[i][0] += 1 // 스테이지에 도달
                }
                for (i in 0 until stage - 1) {
                    record[i][1] += 1 // 스테이지 클리어
                }
            }
        }
        for (i in record.indices) {
            println(record[i].toList())
        }
        val failureRate = record.flatMap {
            if (it[0] == 0) {
                listOf(0.0)
            } else {
                listOf((it[0] - it[1]).toDouble() / it[0])
            }
        }
        println(failureRate)

        answer = answer.sortedByDescending { failureRate[it - 1] }.toIntArray()

        // println(answer.toList())
        return answer
    }
}

fun main() {
    FailureRate().solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3))
}