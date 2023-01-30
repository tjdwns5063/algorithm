import kotlin.math.min

class Boj16236 {
    val n = readln().toInt()
    val graph = Array(n) { readln().split(' ').map { it.toInt() }.toIntArray() }
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val queue = ArrayDeque<Pair<Int, Int>>()
    var size = 2
    var timer = 0
    var currEat = 0
    val targetList = mutableListOf<Triple<Int, Int, Int>>()

    fun bfs(x: Int, y: Int) {
        graph[x][y] = 0
        val copyGraph = Array(n) { graph[it].copyOf() }
        val visited = Array(n) { BooleanArray(n) { false } }

        queue += Pair(x, y)
        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            queue.removeFirst()
            visited[currX][currY] = true
            if (graph[currX][currY] < size && graph[currX][currY] != 0)
                targetList += Triple(currX, currY, copyGraph[currX][currY])
            for (i in dx.indices) {
                val (nx, ny) = Pair(currX + dx[i], currY + dy[i])

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue
                if (visited[nx][ny] || graph[nx][ny] > size || graph[nx][ny] == 9)
                    continue
                visited[nx][ny] = true
                copyGraph[nx][ny] = copyGraph[currX][currY] + 1
                queue += Pair(nx, ny)
            }
        }
    }

    fun solution() {
        while (true) {
            for (i in graph.indices) {
                for (j in graph[i].indices) {
                    if (graph[i][j] == 9)
                        bfs(i, j)
                }
            }
            if (targetList.isEmpty())
                break
            else {
                val targets = targetList
                    .sortedWith(compareBy<Triple<Int, Int, Int>>(
                        { it.third},
                        { it.first },
                        { it.second }))
                val target = targets[0]
                graph[target.first][target.second] = 9
                ++currEat
                timer += target.third
                if (currEat == size) {
                    currEat = 0
                    ++size
                }
                targetList.clear()
            }
        }
        println(timer)
    }
}

fun main() {
    Boj16236().solution()
}