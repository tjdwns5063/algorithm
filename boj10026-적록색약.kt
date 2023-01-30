class Boj10026 {
    val size: Int
    val graph: Array<CharArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        size = readln().toInt()
        graph = Array(size) { readln().toCharArray() }
    }

    fun bfs(visited: Array<BooleanArray>) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var ans = 0

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j])
                    continue
                queue += Pair(i, j)
                ++ans
                while (queue.isNotEmpty()) {
                    val (currX, currY) = queue.first()
                    queue.removeFirst()
                    visited[currX][currY] = true
                    for (k in dx.indices) {
                        val (nx, ny) = Pair(currX + dx[k], currY+ dy[k])

                        if (nx < 0 || ny < 0 || nx >= size || ny >= size)
                            continue
                        if (visited[nx][ny] || graph[currX][currY] != graph[nx][ny])
                            continue
                        queue += Pair(nx, ny)
                        visited[nx][ny] = true
                    }
                }
            }
        }
        print("$ans ")
    }

    fun solution() {
        bfs(Array(size) { BooleanArray(size) { false } })
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == 'G')
                    graph[i][j] = 'R'
            }
        }
        bfs(Array(size) {BooleanArray(size) { false } })
    }
}

fun main() {
    Boj10026().solution()
}