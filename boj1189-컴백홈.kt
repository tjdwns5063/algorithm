class Boj1189 {
    val row: Int
    val col: Int
    val dist: Int
    val map: Array<CharArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var ans = 0

    init {
        val input = readln().split(' ').map { it.toInt() }
        row = input[0]
        col = input[1]
        dist = input[2]
        map = Array(row) { readln().toCharArray() }
    }

    fun dfs(x: Int, y: Int, visit: Array<BooleanArray>, currDist: Int) {
        if (x == 0 && y == col - 1) { // 오른쪽 모서리에 도달하면 집에 도착.
            if (dist == currDist) {
                ans += 1
            }
            return
        }
        visit[x][y] = true
        for (i in dx.indices) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                continue
            if (map[nx][ny] != '.' || visit[nx][ny])
                continue
            dfs(nx, ny, visit, currDist + 1)
        }
        visit[x][y] = false
    }

    fun solution() {
        dfs(row - 1, 0, Array(row) { BooleanArray(col) { false } }, 1) // 왼쪽 모서리에서 한수가 출발.
        println(ans)
    }
}

fun main() {
    Boj1189().solution()
}