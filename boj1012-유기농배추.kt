class Boj1012 {
    var width: Int
    var height: Int
    var graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0 , -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            width = it[0]
            height = it[1]
            graph = Array(height) { IntArray(width) { 0 } }
            repeat(it[2]) {
                readln().split(' ').map { it.toInt() }.also {
                    graph[it[1]][it[0]] = 1
                }
            }
        }
    }

    fun solution() {
        val visited = Array(height) { BooleanArray(width) { false } }
        val queue = ArrayDeque<Pair<Int, Int>>()
        var answer = 0

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] == 0)
                    continue
                queue += Pair(i, j)
                answer++
                while (queue.isNotEmpty()) {
                    val curr = queue.first()

                    queue.removeFirst()
                    visited[curr.first][curr.second] = true
                    for (i in dx.indices) {
                        val (nx, ny) = curr.run { Pair(first + dx[i], second + dy[i]) }

                        if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                            continue
                        if (visited[nx][ny] || graph[nx][ny] == 0)
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
    repeat(readln().toInt()) {
        Boj1012().solution()
    }
}