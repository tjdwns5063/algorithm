class Boj7562 {
    val size: Int
    val curr: Pair<Int, Int>
    val dest: Pair<Int, Int>
    val graph: Array<IntArray>
    val dx = intArrayOf(2, 1, 2, 1, -1, -2, -2, -1)
    val dy = intArrayOf(1, 2, -1, -2, 2, 1, -1, -2)

    init {
        size = readln().toInt()
        curr = readln().split(' ').map { it.toInt() }.let {
            it[0] to it[1]
        }
        dest = readln().split(' ').map { it.toInt() }.let {
            it[0] to it[1]
        }
        graph = Array(size) { IntArray(size) { 0 } }
        graph[curr.first][curr.second] = 1
    }

    fun solution() {
        val queue = ArrayDeque<Pair<Int, Int>>()

        queue += curr
        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            queue.removeFirst()
            for (i in dx.indices) {
                val (nx, ny) = Pair(currX + dx[i], currY + dy[i])

                if (nx < 0 || ny < 0 || nx >= size || ny >= size)
                    continue
                if (graph[nx][ny] != 0)
                    continue
                queue += Pair(nx, ny)
                graph[nx][ny] = graph[currX][currY] + 1
            }
        }
        println(graph[dest.first][dest.second] - 1)
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj7562().solution()
    }
}