import java.util.*
import kotlin.math.*

class Boj11286 {
    val n: Int

    init {
        n = readln().toInt()
    }

    fun solution() {
        val pQ = PriorityQueue<Int>(compareBy({ abs(it) }, { it }))

        repeat(n) {
            val input = readln().toInt()
            when (input) {
                0 -> {if (pQ.isEmpty()) println(0) else println(pQ.poll())}
                else -> pQ += input
            }
        }
    }
}

fun main() {
    Boj11286().solution()
}