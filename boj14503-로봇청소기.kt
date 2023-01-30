class Boj14503 {
    val height: Int
    val width: Int
    val graph: Array<IntArray>
    val cleaned: Array<BooleanArray>
    val robot: IntArray
    val dir = arrayOf<Pair<Int, Int>>(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var endFlag = false
    val NORTH = 0
    val EAST = 1
    val SOUTH = 2
    val WEST = 3

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }
        robot = readln().split(' ').map { it.toInt() }.toIntArray()
        graph = Array(height) { readln().split(' ').map { it.toInt() }.toIntArray() }
        cleaned = Array(height) { BooleanArray(width) { false } }
    }

    fun dfs(curr: IntArray) {
        val x = curr[0]
        val y = curr[1]

        cleaned[x][y] = true
        search(curr)

        if (endFlag)
            return
        dfs(curr)
    }

    private fun search(curr: IntArray) {
        val ndir = turnLeft(curr[2])
        val nx = curr[0] + dir[ndir].first
        val ny = curr[1] + dir[ndir].second
        val backX = curr[0] - dir[curr[2]].first
        val backY = curr[1] - dir[curr[2]].second

        if (isAllCleanedOrWall(curr)) {
            if (graph[backX][backY] == 1) {
                endFlag = true
                return
            }
            else {
                curr[0] = backX
                curr[1] = backY
                return
            }
        }
        if (graph[nx][ny] == 0 && !cleaned[nx][ny]) {
            curr[0] = nx
            curr[1] = ny
            curr[2] = ndir
            return
        }
        if (graph[nx][ny] == 1 || cleaned[nx][ny]) {
            curr[2] = ndir
            search(curr)
        }
        return
    }

    private fun turnLeft(dir: Int): Int {
        return if (dir == NORTH) WEST else dir - 1
    }

    private fun isAllCleanedOrWall(curr: IntArray): Boolean {
        var cnt = 0
        for (i in dir.indices) {
            val nx = curr[0] + dir[i].first
            val ny = curr[1] + dir[i].second

            if (cleaned[nx][ny] || graph[nx][ny] == 1)
                ++cnt
        }
        return cnt == 4
    }

    fun solution() {
        dfs(robot)
        var ans = 0
        for (i in cleaned.indices) {
            for (j in cleaned[i].indices) {
                if (cleaned[i][j])
                    ++ans
            }
        }
        println(ans)
    }
}

fun main() {
    Boj14503().solution()
}