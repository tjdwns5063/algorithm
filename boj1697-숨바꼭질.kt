import kotlin.math.min

class Boj1697 {
    val n: Int
    val k: Int
    init {
        val input = readln().split(' ').map { it.toInt() }
        n = input[0]
        k = input[1]
    }
    fun solution() {
        val graph = IntArray(100004) { 0 }
        graph[n] = 1

        val q = ArrayDeque<Int>()
        q += n
        while (q.isNotEmpty()) {
            val curr = q.first()
            q.removeFirst()
            if (curr == k)
                break
            val dx = arrayOf(-1, 1, curr)
            for (i in dx.indices) {
                val nx = curr + dx[i]
                if (nx < 0 || nx >= 100004)
                    continue
                if (graph[nx] == 0) {
                    q += nx
                    graph[nx] = graph[curr] + 1
                }
            }
        }
        println(graph[k] - 1)
    }
}

fun main() {
    Boj1697().solution()
}