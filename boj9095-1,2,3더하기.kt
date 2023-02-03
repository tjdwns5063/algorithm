// time = 1sec
// memory = 512mb
// n = 1 <= n <= 10

class Boj9095 {
    val n: Int
    val dp: IntArray

    init {
        n = readln().toInt()
        dp = IntArray(11) { 0 }
    }

    fun solution() {
        dp[1] = 1
        dp[2] = 2
        dp[3] = 4
        
        for (i in 4..10) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
        }
        println(dp[n])
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj9095().solution()
    }
}