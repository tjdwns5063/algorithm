import kotlin.math.*

class Boj1654 {
    val n: Long
    val need: Long
    val cables: LongArray

    init {
        readln().split(' ').map { it.toLong() }.also {
            n = it[0]
            need = it[1]
        }
        cables = LongArray(n.toInt()) { readln().toLong() }
        cables.sort()
    }

    fun solution() {
        var start: Long = 1
        var end: Long = Int.MAX_VALUE.toLong()

        while (start < end) {
            var mid: Long = (start + end + 1) / 2

            if (checkPossible(mid)) {
                start = mid
            } else {
                end = mid - 1
            }
        }
        println(start)
    }

    fun checkPossible(target: Long): Boolean {
        var cnt = 0L
        for (i in cables.indices) {
            cnt += cables[i] / target
        }
        return cnt >= need
    }
}

fun main() {
    Boj1654().solution()
}