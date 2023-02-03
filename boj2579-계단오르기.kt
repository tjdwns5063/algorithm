// time = 1sec
// memory= 128MB
// n = 1 <= n <= 300

import kotlin.math.*

class Boj2579 {
    val n: Int
    val stairs: IntArray
    val dp: IntArray

    init {
        n = readln().toInt()
        stairs = IntArray(301) { 0 }
        repeat(n) {
            stairs[it + 1] = readln().toInt()
        }
        dp = IntArray(301) { 0 }
    }

    fun solution() {
        dp[1] = stairs[1]
        dp[2] = stairs[1] + stairs[2]
        dp[3] = max(stairs[1] + stairs[3], stairs[2] + stairs[3])
        
        for (i in 4..n) {
            dp[i] = max(dp[i-2] + stairs[i], dp[i-3] + stairs[i - 1] + stairs[i])
        }
        println(dp[n])
    }
}

fun main() {
    Boj2579().solution()
}