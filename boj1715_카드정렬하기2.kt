import java.util.*

class Boj1715 {
    val n: Int
    val pQ: PriorityQueue<Int>

    init {
        n = readln().toInt()
        pQ = PriorityQueue<Int> { first, second -> first - second }

        repeat(n) {
            pQ += readln().toInt()
        }
    }

    fun solution() {
        var ans = 0
    
        while (pQ.size > 1) {
            val card1 = pQ.poll()
            val card2 = pQ.poll()

            ans += card1 + card2
            pQ += card1 + card2
        }
        println(ans)
    }
}

fun main() {
    Boj1715().solution()
}