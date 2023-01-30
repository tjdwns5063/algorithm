import kotlin.math.*

class Boj2805 {
    val n: Int
    val need: Long
    val trees: LongArray
    
    init {
        readln().split(' ').also {
            n = it[0].toInt()
            need = it[1].toLong()
        }

        trees = readln().split(' ').map { it.toLong() }.toLongArray()
        trees.sort()
    }
    
    fun solution() {
        var start: Long = 0L
        var end: Long = trees[trees.lastIndex]
        var ans: Long = 0L

        while (start <= end) {
            var mid: Long = (start + end) / 2

            if (checkPossible(mid)) {
                ans = mid
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        println(ans)
        // println("start: $start end: $end")
    }

    fun checkPossible(mid: Long): Boolean {
        var len: Long = 0L
        
        for (i in trees.indices) {
            if (trees[i] > mid) {
                len += (trees[i] - mid)
            }
        }
        return len >= need
    }
}

fun main() {
    Boj2805().solution()
}