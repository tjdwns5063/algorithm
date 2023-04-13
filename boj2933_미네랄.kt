import java.util.*

// time = 1sec
// space = 128mb
// 1  <= r, c <= 100
// 1 <= n <= 100

class Boj2933 {
    val row: Int
    val col: Int
    val minerals: Array<CharArray>
    val n: Int
    val sticks: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            row = it[0]
            col = it[1]
        }
        minerals = Array(row) { readln().toCharArray() }
        n = readln().toInt()
        sticks = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun solution() {
        sticks.forEachIndexed { person, stick ->
            throwStick(stick, person)
            val clusters = exploreMap()

            clusters.forEach { cluster -> 
                if (isHover(cluster)) {
                    fallCluster(cluster)
                }
            }
        }
        printMinerals()
    }

    private fun throwStick(stick: Int, person: Int) {
        if (person % 2 == 0) {
            for (y in 0 until col) {
               if (minerals[row - stick ][y] == 'x') {
                    minerals[row - stick][y] = '.'
                    return
                }
            }
        } else {
            for (y in col-1 downTo 0) {
               if (minerals[row - stick][y] == 'x') {
                    minerals[row - stick][y] = '.'
                    return
                }
            }
        }
    }

    private fun exploreMap(): List<List<Pair<Int, Int>>> {
        val visited: Array<BooleanArray> = Array(row) { BooleanArray(col) { false } }
        val clusters: MutableList<List<Pair<Int, Int>>> = mutableListOf()
        
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (minerals[i][j] == '.' || visited[i][j])
                    continue
                clusters += bfs(i, j, visited)
            }
        }
        return clusters
    }
    
    private fun bfs(x: Int, y: Int, visited: Array<BooleanArray>): List<Pair<Int, Int>> {
        val cluster = mutableListOf<Pair<Int, Int>>()
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        val dx = intArrayOf(1, 0, -1, 0)
        val dy = intArrayOf(0, 1, 0, -1)

        queue += Pair(x, y)
        cluster += Pair(x, y)

        while (queue.isNotEmpty()) {
            val (currX, currY) = queue.poll()

            visited[currX][currY] = true
            for (i in 0 until 4) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]

                if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                    continue
                if (visited[nx][ny] || minerals[nx][ny] == '.')
                    continue
                visited[nx][ny] = true
                queue += Pair(nx, ny)
                cluster += Pair(nx, ny)
            }
        }
        return cluster
    }

    private fun isHover(cluster: List<Pair<Int, Int>>): Boolean {
        return if (cluster.count { it.first == row - 1 } > 0) false else true
    }

    private fun fallCluster(cluster: List<Pair<Int, Int>>) {
            val coords = cluster.sortedWith(compareBy({it.second}, {-it.first})).toMutableList()
            val bottoms = filterBottomList(coords).toMutableList()

            repeat(row) {
                for ((idx, bottom) in bottoms.withIndex()) {
                    var (currX, currY) = bottom
                    if (currX + 1 >= row || minerals[currX + 1][currY] != '.')
                        return@repeat
                    bottoms[idx] = Pair(currX + 1, currY)
                }
                for ((idx, curr) in coords.withIndex()) {
                    var (currX, currY) = curr

                    minerals[currX + 1][currY] = 'x'
                    minerals[currX][currY] = '.'
                    coords[idx] = Pair(currX + 1, currY)
                }
            }
    }

    private fun filterBottomList(cluster: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val bottomList: MutableList<Pair<Int, Int>> = mutableListOf()

        for (coord in cluster) {
            if (coord.first >= row - 1) {
                continue
            }
            if (minerals[coord.first + 1][coord.second] == 'x')
                continue 
            bottomList += coord
        }
        return bottomList
    }

    private fun printMinerals() {
        for (i in 0 until row) {
            for (j in 0 until col) {
                print(minerals[i][j])
            }
            println()
        }
    }
}

fun main() {
    Boj2933().solution()
}