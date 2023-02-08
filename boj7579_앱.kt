// time = 1sec
// memory = 128mb
// n = 1 <= n <= 100
// m = 1 <= m <= 10,000,000
// time_complex = 10000 * n

import kotlin.math.*

class Boj7579 {
    val n: Int
    val m: Int
    val memories: IntArray
    val costs: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }

        memories = readln().split(' ').map { it.toInt() }.toIntArray()
        costs = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun solution() {
        val dp = Array(n+1) { IntArray(10001) { 0 } }

        for (i in 1..n) {
            for (j in 1..10000) {
                if (costs[i-1] <= j) {
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j - costs[i-1]] + memories[i-1])
                } else {
                    dp[i][j] = dp[i-1][j]
                }
            }
        }

        var ans = Int.MAX_VALUE
        for (i in 1..n) {
            for (j in 1..10000) {
                if (dp[i][j] >= m) {
                    ans = min(ans, j)
                }
            }
        }

        println(ans)
    }
}

fun main() {
    Boj7579().solution()
}