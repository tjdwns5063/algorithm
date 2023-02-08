import kotlin.math.*

class Boj23971 {
    val h: Int
    val w: Int
    val n: Int
    val m: Int

    init {
        readln().split(' ').map { it.toInt() }.also {
            h = it[0]
            w = it[1]
            n = it[2]
            m = it[3]
        }
    }

    fun solution() {
        var ans = 0

        for (currX in 0 until h step(n+1)) {
            for (currY in 0 until w step(m+1)) {
                ++ans
            }
        }
        println(ans)
    }
}

fun main() {
    Boj23971().solution()
}