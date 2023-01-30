import kotlin.math.*

class boj10816 {
    val n: Int
    val cards: IntArray
    val m: Int
    val targets: IntArray

    init {
        n = readln().toInt()
        cards = readln().split(' ').map {it.toInt()}.toIntArray()
        m = readln().toInt()
        targets  = readln().split(' ').map {it.toInt()}.toIntArray()

        cards.sort()
    }

    fun lower_bound(target: Int): Int {
        var start = 0
        var end = cards.size
        while (start < end) {
            var mid = (start + end) / 2
            if (cards[mid] >= target) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        // println(cards.contentToString())
        // println("Start: $start end: $end")
        return start
    }

    fun upper_bound(target: Int): Int {
        var start = 0
        var end = cards.size
        
        while (start < end) {
            var mid = (start + end) / 2
            if (cards[mid] > target) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        // println("start: $start end: $end")
        return end
    }

    fun solution() {
        var ans = StringBuilder()
        for (target in targets) {
            ans.append("${abs(upper_bound(target) - lower_bound(target))} ")
        }
        println(ans)
    }
}

fun main() {
    boj10816().solution()
}