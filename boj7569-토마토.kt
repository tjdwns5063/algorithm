import kotlin.math.max
import kotlin.system.exitProcess

class Boj7569 {
    val x: Int
    val y: Int
    val z: Int
    val graph: Array<Array<IntArray>>
    val queue: ArrayDeque<Triple<Int, Int, Int>>
    val dx = intArrayOf(1, 0, -1, 0, 0, 0)
    val dy = intArrayOf(0, 1, 0, -1, 0, 0)
    val dz = intArrayOf(0, 0, 0, 0, 1, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            y = it[0]
            x = it[1]
            z = it[2]
        }

        graph = Array(z) { Array(x) { readln().split(' ').map { it.toInt() }.toIntArray() } }
        queue = ArrayDeque<Triple<Int, Int, Int>>().apply {
            for (z in graph.indices) {
                for (i in graph[z].indices) {
                    for (j in graph[z][i].indices) {
                        if (graph[z][i][j] == 1)
                            add(Triple(z, i, j))
                    }
                }
            }
        }
    }

    fun solution() {
//        println(queue)
        while (queue.isNotEmpty()) {
            val (currZ, currX, currY) = queue.first()
            queue.removeFirst()
            for (i in dx.indices) {
                val (nz, nx, ny) = Triple(currZ + dz[i], currX + dx[i], currY + dy[i])

                if (nz < 0 || nx < 0 || ny < 0 || nz >= z || nx >= x || ny >= y)
                    continue
                if (graph[nz][nx][ny] != 0)
                    continue
                queue += Triple(nz, nx, ny)
                graph[nz][nx][ny] = graph[currZ][currX][currY] + 1
            }
        }
        var ans = 0

        for (z in graph.indices) {
            for (i in graph[z].indices) {
                for (j in graph[z][i].indices) {
                    if (graph[z][i][j] == 0) {
                        println(-1)
                        exitProcess(0)
                    }
                    ans = max(ans, graph[z][i][j])
                }
            }
        }
        println(ans - 1)
    }
}

fun main() {
    Boj7569().solution()
}