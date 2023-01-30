import java.util.PriorityQueue
import kotlin.system.exitProcess

class Boj4485(val iter: Int) {
    val size = readln().toInt()
    val graph = Array(size) { readln().split(' ').map { it.toInt() }.toIntArray() }
    val distArr = Array(size) { IntArray(size) { Int.MAX_VALUE } }
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    fun solution() {
        if (size == 0)
            exitProcess(0)
        dijkstra()
        println("Problem $iter: ${distArr[size - 1][size - 1]}")
    }

    private fun dijkstra() {
        val pQ = PriorityQueue<Pair<Pair<Int, Int>, Int>> { first, second ->
            first.second - second.second
        }

        distArr[0][0] = graph[0][0]
        pQ += Pair(Pair(0, 0), graph[0][0])
        while (pQ.isNotEmpty()) {
            val (currNode, currDist) = pQ.poll()

            if (currDist != distArr[currNode.first][currNode.second])
                continue
            for (i in dx.indices) {
                val nx = currNode.first + dx[i]
                val ny = currNode.second + dy[i]

                if (nx < 0 || ny < 0 || nx >= size || ny >= size)
                    continue
                if (distArr[nx][ny] <= distArr[currNode.first][currNode.second] + graph[nx][ny])
                    continue
                distArr[nx][ny] = distArr[currNode.first][currNode.second] + graph[nx][ny]
                pQ += Pair(Pair(nx, ny), distArr[nx][ny])
            }
        }
    }
}

fun main() {
    var idx = 1
    while (true) {
        Boj4485(idx).solution()
        ++idx
    }
}