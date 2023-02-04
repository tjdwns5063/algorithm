import kotlin.math.*

// time = 2sec
// memory = 512mb
// n = 1 <= n <= 100
// k = 1 <= k <= 100000
// time complex = O(nk)

class Boj12865 {
    val n: Int
    val k: Int
    val items: MutableList<Pair<Int, Int>>
    val dp: Array<Array<Int>>
    
    init {
        readLine()!!.split(' ').map{ it.toInt() }.also {
            n = it[0]
            k = it[1]
        }

        items = mutableListOf()
        items += 0 to 0
        repeat(n) {
            items += readLine()!!.split(' ').map { it.toInt() }.let {
                it[0] to it[1]
            }
        }
        dp = Array(n+1) { Array(k+1) { 0 } }
    }

    fun solution() {
        // println(items)

        for (i in 1..n) {
            for (j in 1..k) {
                if (items[i].first <= j)
                    dp[i][j] = max(dp[i-1][j], dp[i-1][j-items[i].first]+items[i].second)
                else
                    dp[i][j] = dp[i-1][j]
            }
        }
        println(dp[n][k])
    }
}

fun main() {
    Boj12865().solution()
}