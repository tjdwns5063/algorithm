import kotlin.math.max

class Boj2568 {
    val size: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var answer = 0

    init {
        size = readln().toInt()
        graph = Array(size) { readln().split(' ').map { it.toInt() }.toIntArray() }
    }

    fun bfs(height: Int): Int {
        val visited = Array(size) { BooleanArray(size) { false } }
        val queue = ArrayDeque<Pair<Int, Int>>()
        var count = 0
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] <= height)
                    continue
                queue += Pair(i, j)
                ++count
                while (queue.isNotEmpty()) {
                    val (currX, currY) = queue.first()
                    queue.removeFirst()
                    visited[currX][currY] = true
                    for (k in dx.indices) {
                        val (nx, ny) = Pair(currX + dx[k], currY + dy[k])

                        if (nx < 0 || ny < 0 || nx >= size || ny >= size)
                            continue
                        if (visited[nx][ny] || graph[nx][ny] <= height)
                            continue
                        queue += Pair(nx, ny)
                        visited[nx][ny] = true
                    }
                }
            }
        }
//        println("count: $count")
        answer = max(answer, count)
        return count
    }

    fun solution() {
        var height = 0

        while (true) {
            if (bfs(height) == 0) {
                break
            }
            ++height
        }
        println(answer)
    }
}

fun main() {
    Boj2568().solution()
}