import java.util.*
import kotlin.math.*

class Boj1943 {
    val n: Int
    val coins: IntArray
    val cnts: IntArray
    
    init {
        n = readln().toInt()
        coins = IntArray(n+1) { 0 }
        cnts = IntArray(n+1) { 0 }
        repeat(n) { idx ->
            readln().split(' ').map { it.toInt() }.also {
                coins[idx+1] = it[0]
                cnts[idx+1] = it[1] * coins[idx+1]  
            }
        }
    }

    fun solution() {
        val dp = Array(n+1) { IntArray(100001) { 0 } }
        val target = cnts.sum() / 2
        // println(coins.contentToString())
        // println(cnts.contentToString())
        // println(target)
        for (i in 1..n) {
            println(coins[i])
            for (j in 0..target step(coins[i])) {
                if (j >= coins[i]) {
                    
                } else {
                
                }
            }
            // println()
        }
        if (dp[n][target] != 0)
            println(1)
        else
            println(0)
    }
}

fun main() {
    // repeat(3) {
        Boj1943().solution()
    // }
}