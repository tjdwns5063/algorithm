class Boj5014 {
    val f: Int
    val s: Int
    val g: Int
    val u: Int
    val d: Int
    val graph: IntArray
    val dir: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            f = it[0]
            s = it[1]
            g = it[2]
            u = it[3]
            d = it[4]
        }
        graph = IntArray(1000004) { 0 }
        graph[s] = 1
        dir = intArrayOf(u, -d)
    }

    fun bfs() {
        val queue = ArrayDeque<Int>()
        queue += s
        
        while (queue.isNotEmpty()) {
            val curr = queue.first()
            queue.removeFirst()

            for (i in dir.indices) {
                val next = curr + dir[i]

                if (next < 1 || next > f)
                    continue
                if (graph[next] != 0)
                    continue
                graph[next] = graph[curr] + 1
                queue += next
            }
        }
    }

    fun solution() {
        bfs()
        // println(graph.contentToString())
        if (graph[g] != 0) {
            println(graph[g] - 1)
        } else {
            println("use the stairs")
        }
    }
}

fun main() {
    Boj5014().solution()
}