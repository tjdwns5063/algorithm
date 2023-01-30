import kotlin.math.max

class Boj1926 {
    val dx = intArrayOf(1,0,-1,0)
    val dy = intArrayOf(0,1,0,-1)
    val width: Int
    val height: Int

    init {
        val input = readln().split(' ').map { it.toInt() }
        height = input[0]
        width= input[1]
    }

    fun bfs(graph: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var ans = 1
        queue += Pair(x, y)
        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            visited[curr.first][curr.second] = true
            for (i in 0 until 4) {
                val nx = curr.first + dx[i]
                val ny = curr.second + dy[i]

                if (nx >= height || ny >= width || nx < 0 || ny < 0)
                    continue
                if (graph[nx][ny] == 0)
                    continue
                if (!visited[nx][ny] && graph[nx][ny] == 1) {
                    queue += Pair(nx, ny)
                    visited[nx][ny] = true
                    ++ans
                }
            }
        }
        return (ans)
    }

    fun solution() {
        val graph = Array(height) { readln().split(' ').map { i -> i.toInt() }.toIntArray() }
        val visited = Array(height) { BooleanArray(width) { false } }
        var cnt = 0
        var mx = 0

//        println(graph.contentDeepToString())
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] == 0)
                    continue
                mx = max(mx, bfs(graph, visited, i, j))
                ++cnt
            }
        }
        println(cnt)
        println(mx)
    }
}

fun main() {
    Boj1926().solution()
}