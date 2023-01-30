class Boj2573 {
    val height: Int
    val width: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0 , -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }
        graph = Array(height) { readln().split(' ').map { it.toInt() }.toIntArray() }
    }

    fun meltdown() {
        val visited = Array(height) { BooleanArray(width) { false } }
        val queue = ArrayDeque<Pair<Int, Int>>()

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] > 0)
                    queue += Pair(i, j)
            }
        }

        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            queue.removeFirst()
            visited[currX][currY] = true
            var cnt = 0

            for (i in dx.indices) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]

                if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue
                if (visited[nx][ny])
                    continue
                if (graph[nx][ny] <= 0) {
                    ++cnt
                }
            }
            graph[currX][currY] -= cnt
        }
    }

    fun dfs(start: Pair<Int, Int>, visited: Array<BooleanArray>) {
        val (currX, currY) = start

        visited[currX][currY] = true
        for (i in dx.indices) {
            val nx = currX + dx[i]
            val ny = currY + dy[i]

            if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                continue
            if (graph[nx][ny] <= 0 || visited[nx][ny])
                continue
            dfs(Pair(nx, ny), visited)
        }
    }

    fun solution() {
        var year = 1

        while (true) {
            var cnt = 0
            val visited = Array(height) { BooleanArray(width) { false } }

            meltdown()
//            for (i in graph.indices) {
//                for (j in graph.indices) {
//                    print("${graph[i][j]} ")
//                }
//                println()
//            }
            for (i in graph.indices) {
                for (j in graph[i].indices) {
                    if (graph[i][j] <= 0 || visited[i][j])
                        continue
                    ++cnt
                    dfs(Pair(i,j), visited)
                }
            }
            if (cnt > 1) {
                println(year)
                return
            }
            if (cnt == 0) {
                println(0)
                return
            }
            ++year
        }
    }
}

fun main() {
    Boj2573().solution()
}