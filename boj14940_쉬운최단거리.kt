class Boj14940 {
    val n: Int
    val m: Int
    val graph: Array<IntArray>
    val board: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        graph = Array(n) { readln().split(' ').map { it.toInt() }.toIntArray() }
        board = Array(n) { IntArray(m) { 0 } }
    }

    fun bfs() {
        val queue = ArrayDeque<Pair<Int, Int>>()

        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == 2)
                    queue += i to j
            }
        }
        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.first()
            queue.removeFirst()
            for (i in dx.indices) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue
                if (graph[nx][ny] != 1 || board[nx][ny] != 0)
                    continue
                board[nx][ny] = board[currX][currY] + 1
                queue += nx to ny
            }
        }
    }

    fun solution() {
        bfs()

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] != 0)
                    print("${board[i][j]} ")
                else {
                    if (graph[i][j] == 0 || graph[i][j] == 2)
                        print("0 ")
                    else if (graph[i][j] == 1)
                        print("-1 ")
                }
            }
            println()
        }
    }
}

fun main() {
    Boj14940().solution()
}