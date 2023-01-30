class Boj7576 {
    val w: Int
    val h: Int
    val graph: Array<IntArray>
    val visit: Array<BooleanArray>
    val queue: ArrayDeque<Pair<Int, Int>>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        val input = readln().split(' ').map { it.toInt() }
        w = input[0]
        h = input[1]
        graph = Array(h) { readln().split(' ').map { it.toInt() }.toIntArray() }
        visit = Array(h) { BooleanArray(w) { false } }
        queue = ArrayDeque()
    }
    fun bfs() {
        while (queue.isNotEmpty()) {
            val curr = queue.first()
            queue.removeFirst()
            visit[curr.first][curr.second] = true
            for (i in dx.indices) {
                val nx = curr.first + dx[i]
                val ny = curr.second + dy[i]

                if (nx < 0 || ny < 0 || nx >= h || ny >= w)
                    continue
                if (visit[nx][ny] || graph[nx][ny] != 0)
                    continue
                graph[nx][ny] = graph[curr.first][curr.second] + 1
                visit[nx][ny] = true
                queue += Pair(nx, ny)
            }
        }
    }

    fun solution() {
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == 1)
                    queue += Pair(i, j)
            }
        }
        bfs()
//        graph.forEach { println(it.contentToString()) }
        var ans = -1
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] > ans)
                    ans = graph[i][j]
                if (graph[i][j] == 0) {
                    println(-1)
                    return
                }
            }
        }
        println(ans - 1)
    }
}

fun main() {
    Boj7576().solution()
}