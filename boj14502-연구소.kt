import kotlin.math.max

class Boj14502 {
    val height: Int
    val width: Int
    val graph: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    val comb = mutableListOf<Pair<Int, Int>>()
    var answer = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }

        graph = Array(height) {
            readln().split(' ').map { it.toInt() }.toIntArray()
        }

    }

    fun dfs(depth: Int, visit: Array<BooleanArray>) {
        if (comb.size == 3) {
//            println(comb)
            comb.forEach {
                graph[it.first][it.second] = 1
            }
            answer = max(bfs(), answer)
            comb.forEach {
                graph[it.first][it.second] = 0
            }
            return
        }

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (visit[i][j] || graph[i][j] != 0)
                    continue
                visit[i][j] = true
                comb += Pair(i, j)
                dfs(depth + 1, visit)
                comb.removeLast()
                visit[i][j] = false
            }
        }
    }

    fun bfs(): Int {
        val tempGraph = Array(height) { graph[it].copyOf() }
        val queue = ArrayDeque<Pair<Int, Int>>()

        for (i in tempGraph.indices) {
            for (j in tempGraph[i].indices) {
                if (tempGraph[i][j] == 2) {
                    queue += Pair(i, j)
                }
            }
        }

        while (queue.isNotEmpty()) {
            val curr = queue.first()
            queue.removeFirst()
            for (i in dx.indices) {
                val (nx, ny) = curr.run { Pair(first + dx[i], second + dy[i]) }

                if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue
                if (tempGraph[nx][ny] != 0)
                    continue
                queue += Pair(nx, ny)
                tempGraph[nx][ny] = 2
            }
        }
        var answer = 0
        for (i in tempGraph.indices) {
            for (j in tempGraph[i].indices) {
//                print("${tempGraph[i][j]} ")
                if (tempGraph[i][j] == 0)
                    answer++
            }
//            println()
        }
        return answer
    }

    fun solution() {
        dfs(0, Array(height) { BooleanArray(width) { false } })
        println(answer)
    }
}

fun main() {
    Boj14502().solution()
}