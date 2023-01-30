import kotlin.math.max

class Boj16198 {
    var n = readln().toInt()
    val bids = readln().split(' ').map { it.toInt() }.toMutableList()
    var ans = 0

    fun dfs(n: Int, energy: Int) {
        if (n == 2) {
            ans = max(ans, energy)
            return
        }
        for (i in 1 until n - 1) {
            val tmp = bids[i]
            val energyTmp = bids[i - 1] * bids[i + 1]
            bids.removeAt(i)
            dfs(n - 1, energy + energyTmp)
            bids.add(i, tmp)
        }
    }

    fun solution() {
        dfs(n, 0)
        println(ans)
    }
}

fun main() {
    Boj16198().solution()
}