class Boj2206 {
    val width: Int
    val height: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }
        graph = Array(height) { readln().toCharArray().map { it.digitToInt() }.toIntArray() }

    }

    fun bfs(): Int {
        val visited = Array(height) { Array(width) {
            intArrayOf(0, 0)
        } }
        val queue = ArrayDeque<Triple<Int, Int, Int>>()



        queue += Triple(0, 0, 1)
        while (queue.isNotEmpty()) {
            for (i in visited.indices) {
                for (j in visited[i].indices) {
                    print("${visited[i][j].contentToString()} ")
                }
                println()
            }
            println()
            val (currX, currY, broken) = queue.first()
            queue.removeFirst()
            if (currX == height - 1 && currY == width - 1)
                return visited[currX][currY][broken] + 1
            for (i in dx.indices) {
                val (nx, ny) = Pair(currX + dx[i], currY + dy[i])

                if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue
                if (visited[nx][ny][broken] == 0 && graph[nx][ny] == 0) {
                    queue += Triple(nx, ny, broken)
                    visited[nx][ny][broken] = visited[currX][currY][broken] + 1
                } else if (broken == 1 && graph[nx][ny] == 1) {
                    queue += Triple(nx, ny, 0)
                    visited[nx][ny][0] = visited[currX][currY][broken] + 1
                }
            }
        }

        return -1
    }

    fun solution() {
        println(bfs())
    }
}

fun main() {
    Boj2206().solution()
}