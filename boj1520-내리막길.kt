class Boj1520 {
    val height: Int
    val width: Int
    val graph: Array<IntArray>
    val visited: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var ans = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }
        graph = Array(height) { readln().split(' ').map { it.toInt() }.toIntArray() }
        visited = Array(height) { IntArray(width) { -1 } }
    }

    fun dfs(start: Pair<Int, Int>): Int {
        val (currX, currY) = start

        if (visited[currX][currY] != -1)
            return visited[currX][currY]

        if (currX == height - 1 && currY == width - 1)
            return 1

        visited[currX][currY] = 0

        for (i in dx.indices) {
            val nx = currX + dx[i]
            val ny = currY + dy[i]

            if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                continue
            if (graph[currX][currY] > graph[nx][ny]) {
                visited[currX][currY] += dfs(Pair(nx, ny))
            }
        }
        return visited[currX][currY]
    }

    fun solution() {
        dfs(Pair(0, 0))
//        for (i in visited.indices) {
//            for (j in visited[i].indices) {
//                print("${visited[i][j]} ")
//            }
//            println()
//        }
        println(visited[0][0])
    }
}

fun main() {
    Boj1520().solution()
}