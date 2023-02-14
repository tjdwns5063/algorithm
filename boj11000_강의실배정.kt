import java.util.*

class Boj11000 {
    val n: Int
    var times: Array<Pair<Int, Int>>
    var ans = 0

    init {
        n = readln().toInt()
        times = Array(n) { readln().split(' ').map { it.toInt() }.let { it[0] to it[1] } }
    }

    fun solution() {
        val pQ = PriorityQueue<Pair<Int, Int>>(compareBy({it.first}, {it.second}))
        val q = ArrayDeque<Pair<Int, Int>>()

        for (i in times.indices) {
            pQ += times[i]
            q += times[i]
        }

        while (pQ.isNotEmpty()) {
            var curr = pQ.poll()
            repeat(q.size) {
                val comp = q.first()

                if (curr.first == comp.first && curr.second == comp.second) {
                    q.removeFirst()
                    q+=comp
                }
                else if (curr.second <= comp.first) {
                    curr = curr.first to comp.second
                    q.removeFirst()
                } else {
                    q.removeFirst()
                    q+=comp
                }
            }
            // println(pQ)
        }
        println(q)
    }
}

fun main() {
    Boj11000().solution()
}