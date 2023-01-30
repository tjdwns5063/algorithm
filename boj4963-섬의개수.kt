import kotlin.system.exitProcess

class Boj4963 {
    val width: Int
    val height: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0, -1, 1, 1, -1)
    val dy = intArrayOf(0, 1, 0, -1, 1, -1, 1, -1)
    val visited: Array<BooleanArray>

    init {
        readln().split(' ').map { it.toInt() }.also {
            width = it[0]
            height = it[1]
        }

        graph = Array(height) { readln().split(' ').map { it.toInt() }.toIntArray() }
        visited = Array(height) { BooleanArray(width) { false } }
    }

    fun solution() {
        if (width == 0 && height == 0)
            exitProcess(0)
        val queue = ArrayDeque<Pair<Int, Int>>()
        var answer = 0
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] != 1)
                    continue
                queue += Pair(i, j)
                ++answer
                while (queue.isNotEmpty()) {
                    val (currX, currY) = queue.first()
                    visited[currX][currY] = true
                    queue.removeFirst()
                    for (k in dx.indices) {
                        val (nx, ny) = Pair(currX + dx[k], currY + dy[k])

                        if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                            continue
                        if (visited[nx][ny] || graph[nx][ny] != 1)
                            continue
                        queue += Pair(nx, ny)
                        visited[nx][ny] = true
                    }
                }
            }
        }
        println(answer)
    }
}

fun main() {
    while (true) {
        Boj4963().solution()
    }
}