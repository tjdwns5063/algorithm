import javax.imageio.ImageTranscoder
import kotlin.math.max

class Boj1987 {
    val row: Int
    val col: Int
    val graph: Array<String>
    val visited: BooleanArray
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)
    var ans = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            row = it[0]
            col = it[1]
        }

        graph = Array(row) { readln() }
        visited = BooleanArray(26) { false }
    }

    fun solution() {
        dfs(Pair(0, 0), 0)
        println(ans + 1)
    }

    fun dfs(start: Pair<Int, Int>, depth: Int) {
        val alphabet = graph[start.first][start.second]
//        println(alphabet)
        visited[getIndex(alphabet)] = true
        ans = max(ans, depth)

        for (i in dx.indices) {
            val nx = start.first + dx[i]
            val ny = start.second + dy[i]

            if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                continue
            val newAlphabet = graph[nx][ny]
            if (visited[getIndex(newAlphabet)])
                continue
            dfs(Pair(nx, ny), depth + 1)
            visited[getIndex(newAlphabet)] = false
        }
    }

    fun getIndex(alphabet: Char): Int = alphabet.code - 'A'.code
}

fun main() {
    Boj1987().solution()
}
