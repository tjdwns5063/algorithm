import java.io.*
import java.util.*

class Boj13144 {
    val n: Int
    val sequence: IntArray
    
    init {
        n = readln().toInt()
        sequence = readln().split(' ').map {it.toInt()}.toIntArray()
    }

    fun solution() {
        var sum = 0L
        val visited = BooleanArray(100001) { false }

        var start = 0
        var end = 0
        while (true) {
            // println("start: $start end: $end")
            if (end == n) {
                sum += end-start
                ++start
                if (start == n)
                    break
            } else if (!visited[sequence[end]]) {
                visited[sequence[end]] = true
                ++end
            } else if (visited[sequence[end]]) {
                sum += end-start
                visited[sequence[start++]] = false
            }
        }
        println(sum)
    }
}

fun main() {
    Boj13144().solution()
}