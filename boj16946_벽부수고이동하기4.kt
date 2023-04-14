import java.util.*

class Boj16946 {
    val n: Int
    val m: Int
    val map: Array<IntArray>
    val visited: Array<IntArray>
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        map = Array(n) { readln().toCharArray().map { it.digitToInt() }.toIntArray() }
        visited = Array(n) { IntArray(m) { 0 } }
    }

    fun solution() {
        val cntList: MutableList<Int> = mutableListOf()
        var visit = 1

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (visited[i][j] != 0 || map[i][j] != 0)
                    continue
                cntList += bfs(i, j, visit)
                ++visit
            }
        }

        // println(cntList)


        for (i in 0 until n) {
            for (j in 0 until m) {
                if (visited[i][j] != 0)
                    continue
                val set: MutableSet<Int> = mutableSetOf()
                
                for (k in 0 until 4) {
                    val nx = i + dx[k]
                    val ny = j + dy[k]

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                        continue
                    if (visited[nx][ny] == 0)
                        continue
                    set.add(visited[nx][ny])
                }
                var cnt = 0
                // println(set)
                set.forEach { cnt += cntList[it-1] }
                map[i][j] = (map[i][j] + cnt) % 10
                // set.clear()
            }
        }

        printMap(map)
    }

    fun bfs(x: Int, y: Int, visit: Int): Int {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        var cnt = 1

        queue.add(Pair(x, y))

        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.poll()

            visited[currX][currY] = visit
            for (i in 0 until 4) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue
                if (visited[nx][ny] != 0 || map[nx][ny] != 0)
                    continue
                queue += Pair(nx, ny)
                visited[nx][ny] = visit
                ++cnt
            }
        }
        return cnt
    }

    fun printMap(arr: Array<IntArray>) {
        val sb = StringBuilder()
        
        for (i in 0 until n) {
            for (j in 0 until m) {
                sb.append(arr[i][j])
            }
            sb.append("\n")
        }
        print(sb)
    }
}

fun main() {
    Boj16946().solution()
}