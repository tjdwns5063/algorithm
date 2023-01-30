import kotlin.math.*

class Boj2110 {
    val n: Int
    val count: Int
    val homes: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            count = it[1]
        }
        homes = IntArray(n) { readln().toInt() }
        homes.sort()
        // println(homes.contentToString())
    }

    fun solution() {
        var ans = 0
        var start = 0
        var end = homes[homes.lastIndex] - homes[0]

        while (start <= end) {
            var mid = (start + end) / 2

            if (checkPossible(mid)) {
                if (ans < mid) {
                    ans = mid
                }
                // ans = max(ans, mid)
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        println(ans)
    }

    fun checkPossible(target: Int): Boolean {
        var cnt = 1
        var curr = 0

        for (next in homes.indices) {
            if (homes[next] - homes[curr] >= target) {
                ++cnt
                curr = next
            }
        }
        // println("target: $target cnt: $cnt")
        return cnt >= count
    }
}

fun main() {
    Boj2110().solution()
}