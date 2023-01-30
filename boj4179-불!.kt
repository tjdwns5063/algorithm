import kotlin.math.min
import kotlin.system.exitProcess

class Boj4179 {
    val h: Int
    val w: Int
    val graph: Array<IntArray>
    val fireQ: ArrayDeque<Pair<Int, Int>>
    val humanQ: ArrayDeque<Pair<Int, Int>>
    val fireDist: Array<IntArray>
    val humanDist: Array<IntArray>
    val visit: Array<BooleanArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        val input = readln().split(' ').map { it.toInt() }
        h = input[0]
        w = input[1]
        graph = Array(h) { readln().toCharArray().map {
            when (it) {
                '#' -> -2
                'J' -> 1
                'F' -> -1
                '.' -> 0
                else -> -4
            }
        }.toIntArray()
        }
        visit = Array(h) { BooleanArray(w) {false} }
        fireQ = ArrayDeque()
        humanQ = ArrayDeque()
        fireDist = Array(h) { graph[it].map { i -> if (i == -1) 1 else if (i == 1) 0 else i }.toIntArray() }
        humanDist = Array(h) { graph[it].map { i -> if (i == -1) 0 else i }.toIntArray() }
    }

    fun bfsFire() {
        while (fireQ.isNotEmpty()) {
            val fire= fireQ.first()
            fireQ.removeFirst()
            visit[fire.first][fire.second] = true
            for (i in dx.indices) {
                val nx = fire.first + dx[i]
                val ny = fire.second + dy[i]

                if (nx < 0 || ny < 0 || nx >= h || ny >= w)
                    continue
                if (visit[nx][ny] || fireDist[nx][ny] != 0)
                    continue
                fireDist[nx][ny] = fireDist[fire.first][fire.second] + 1
                visit[nx][ny] = true
                fireQ += Pair(nx, ny)
            }
        }
    }

    fun bfsHuman() {
        while (humanQ.isNotEmpty()) {
            val human = humanQ.first()
            humanQ.removeFirst()
            visit[human.first][human.second] = true
            for (i in dx.indices) {
                val nx = human.first + dx[i]
                val ny = human.second + dy[i]

                if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                    println(humanDist[human.first][human.second])
                    return
                }
                if (visit[nx][ny] || humanDist[nx][ny] != 0)
                    continue
                if (fireDist[nx][ny] != 0 && fireDist[nx][ny] <= humanDist[human.first][human.second] + 1)
                    continue
                humanDist[nx][ny] = humanDist[human.first][human.second] + 1
                visit[nx][ny] = true
                humanQ += Pair(nx, ny)
            }
        }
        println("IMPOSSIBLE")
    }

    fun solution() {
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == -1)
                    fireQ += Pair(i, j)
                if (graph[i][j] == 1)
                    humanQ += Pair(i, j)
            }
        }

        bfsFire()
        for (i in visit.indices) {
            for (j in visit[i].indices) {
                visit[i][j] = false
            }
        }
        bfsHuman()
    }
}

fun main() {
    Boj4179().solution()
}