class Boj13549 {
    val graph: IntArray
    val start: Int
    val end: Int
    val queue = ArrayDeque<Int>()

    init {
        graph = IntArray(100001) { Int.MAX_VALUE }
        readln().split(' ').map { it.toInt() }.also {
            start = it[0]
            end = it[1]
        }
    }

    fun solution() {
        bfs()
        println(graph[end] - 1)
    }

    fun bfs() {
        queue += start
        graph[start] = 1

        while (queue.isNotEmpty()) {
            val curr = queue.first()
            val dx = intArrayOf(1, -1, curr)

            queue.removeFirst()
            for (i in dx.indices) {
                val nx = curr + dx[i]

                if (nx < 0 || nx >= 100001)
                    continue
                if (graph[nx] <= graph[curr])
                    continue
                if (i == 2) {
                    graph[nx] = graph[curr]
                    queue.addFirst(nx)
                } else {
                    graph[nx] = graph[curr] + 1
                    queue.addLast(nx)
                }
            }
        }
    }
}

fun main() {
    Boj13549().solution()
}