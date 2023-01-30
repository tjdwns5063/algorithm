class Boj2583 {
    val graph: Array<IntArray>
    val width: Int
    val height: Int
    val squareCount: Int
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var count = 0
    var areaList = mutableListOf<Int>()

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
            squareCount = it[2]
        }

        graph = Array(height) { IntArray(width) { 0 } }

        repeat(squareCount) {
            readln().split(' ').map { it.toInt() }.also {
                for (i in it[1] until it[3]) {
                    for (j in it[0] until  it[2]) {
                        graph[i][j] = 1
                    }
                }
            }
        }
    }

    fun bfs(x: Int, y: Int, visited: Array<BooleanArray>) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var area = 1

        queue += Pair(x, y)
        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            visited[currX][currY] = true
            queue.removeFirst()

            for (i in dx.indices) {
                val newCoordinate = Pair(currX + dx[i], currY + dy[i])
                val (nx, ny) = newCoordinate

                if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue
                if (visited[nx][ny] || graph[nx][ny] == 1)
                    continue
                ++area
                queue += newCoordinate
                visited[nx][ny] = true
            }
        }
        areaList += area
    }

    fun solution() {
        val visited = Array<BooleanArray>(height) { BooleanArray(width) { false } }

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] == 1)
                    continue
                count++
                bfs(i, j, visited)
            }
        }
        println(count)
        areaList.sorted().forEach {
            print("$it ")
        }
    }
}

fun main() {
    Boj2583().solution()
}