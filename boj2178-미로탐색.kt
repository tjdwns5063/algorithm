class Boj2178 {
    val h: Int
    val w: Int
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        val input = readln().split(' ').map { it.toInt() }
        h = input[0]
        w = input[1]
    }
    fun bfs(graph: Array<IntArray>, visit: Array<BooleanArray>, x: Int, y: Int) {
        val q = ArrayDeque<Pair<Int, Int>>()
        q += Pair(x, y)
        while (q.isNotEmpty()) {
            val curr = q.first()
            q.removeFirst()
            visit[curr.first][curr.second] = true
            for (i in dx.indices) {
                val nx = curr.first + dx[i]
                val ny = curr.second + dy[i]

                if (nx < 0 || ny < 0 || nx >= h || ny >= w)
                    continue
                if (graph[nx][ny] == 0 || visit[nx][ny])
                    continue
                graph[nx][ny] = graph[curr.first][curr.second] + 1
                q += Pair(nx, ny)
                visit[nx][ny] = true
            }
        }
    }

    fun solution() {
        val graph = Array(h) {
            readln().toCharArray().map { it.digitToInt() }.toIntArray()
        }
        val visit = Array(h) { BooleanArray(w) { false } }
//        graph.forEach { println(it.contentToString()) }

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == 0 || visit[i][j])
                    continue
                bfs(graph, visit, i, j)
//                graph.forEach { println(it.contentToString()) }
            }
        }
        println(graph[h - 1][w - 1])
    }
}

fun main() {
    Boj2178().solution()
}