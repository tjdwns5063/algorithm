import kotlin.math.abs

class Boj16234 {
    val size: Int
    val minPopulation: Int
    val maxPopulation: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val unions = mutableListOf<MutableList<Pair<Int, Int>>>()
    var ans = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            size = it[0]
            minPopulation = it[1]
            maxPopulation = it[2]
        }

        graph = Array(size) { readln().split(' ').map { it.toInt() }.toIntArray() }
    }

    fun bfs(x: Int, y: Int, visited: Array<BooleanArray>) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val union = mutableListOf<Pair<Int, Int>>()
        queue += Pair(x, y)
        union += Pair(x, y)

        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            queue.removeFirst()
            visited[currX][currY] = true
            for (i in dx.indices) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]

                if (nx < 0 || ny < 0 || nx >= size || ny >= size)
                    continue
                if (visited[nx][ny])
                    continue

                val populationDiff = abs(graph[currX][currY] - graph[nx][ny])
                if (populationDiff in minPopulation..maxPopulation) {
                    queue += Pair(nx, ny)
                    union += Pair(nx, ny)
                    visited[nx][ny] = true
                }
            }
        }
        if (union.size > 1)
            unions += union
    }

    fun movePopulation(union: MutableList<Pair<Int, Int>>) {
        var sum = union.fold(0) { acc, (x, y) ->
            acc + graph[x][y]
        }
        union.forEach { (x, y) ->
            graph[x][y] = sum / union.size
        }
    }

    fun solution() {
        repeat(2000) {
            val visited = Array(size) { BooleanArray(size) { false } }

            for (i in graph.indices) {
                for (j in graph[i].indices) {
                    bfs(i, j,visited)
                }
            }
            if (unions.isEmpty())
                return@repeat
            else {
                unions.forEach { union -> movePopulation(union) }
                ++ans
                unions.clear()
            }
        }
        println(ans)
    }
}

fun main() {
    Boj16234().solution()
}
