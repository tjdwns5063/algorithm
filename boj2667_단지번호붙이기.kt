class Boj2667 {
    val lineCount = readln().toInt()
    val graph = Array(lineCount) { readln() }
    val visited = Array(lineCount) { BooleanArray(lineCount) { false } }
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0 ,-1)
    var count = 0


    fun bfs(x: Int, y: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        var ret = 1
        count += 1

        queue += Pair(x, y)
        visited[x][y] = true
        while (queue.isNotEmpty()) {
            val curr = queue.first()
            queue.removeFirst()
            visited[curr.first][curr.second] = true
            for (i in dx.indices) {
                val (nx, ny) = curr
                    .run { Pair(first + dx[i], second + dy[i]) }

                if (nx < 0 || ny < 0 || nx >= lineCount || ny >= lineCount)
                    continue
                if (visited[nx][ny] || graph[nx][ny] == '0')
                    continue
//                println("nx: $nx ny: $ny")
                ret += 1
                queue += Pair(nx, ny)
                visited[nx][ny] = true
            }
        }
        return ret
    }

    fun solution() {
//        println(graph.contentToString())
        val counts = mutableListOf<Int>()

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visited[i][j] || graph[i][j] == '0')
                    continue
                counts += bfs(i, j)
            }
        }
        println(count)
        counts.sorted().forEach { println(it) }
    }
}

fun main() {
    Boj2667().solution()
}