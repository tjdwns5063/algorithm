import kotlin.math.abs
import kotlin.math.min

class Boj2230 {
    val n: Int
    val m: Int
    val numbers = mutableListOf<Int>()

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        repeat(n) {
            numbers += readln().toInt()
        }
        numbers.sort()
    }

    fun solution() {
        var start = 0
        var end = 0
        var target = Int.MAX_VALUE

        while (start < n && end < n) {
            val diff = numbers[end] - numbers[start]
            if (diff >= m) {
                target = min(target, diff)
                start += 1
            }
            else
                end += 1
        }
        println(target)
    }
}

fun main() {
    Boj2230().solution()
}