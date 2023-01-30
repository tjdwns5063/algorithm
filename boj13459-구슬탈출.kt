class Boj13459 {
    val height: Int
    val width: Int
    val graph: Array<CharArray>
    var redState: Boolean
    var blueState: Boolean
    val queue = ArrayDeque<MutableList<IntArray>>()
    val RED = 0
    val BLUE = 1
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }

        graph = Array(height) {
            readln().toCharArray()
        }

        redState = false
        blueState = false

        val element = mutableListOf<IntArray>()
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                if (graph[i][j] == 'R' || graph[i][j] == 'B') {
                    element += intArrayOf(i, j, if (graph[i][j] == 'R') RED else BLUE, 0)
                    graph[i][j] = '.'
                }
            }
        }
        queue += element
    }

    fun solution() {
        println(if (bfs()) 1 else 0)
    }


    fun bfs(): Boolean {
        while (queue.isNotEmpty()) {
            val (red, blue) = queue.first().sortedWith(compareBy { it[2] })
            val visited = Array(height) { Array(width) { BooleanArray(2) { false } } }

            queue.removeFirst()

            visited[red[0]][red[1]][red[2]] = true
            visited[blue[0]][blue[1]][blue[2]] = true
            if (red[3] > 9 || blue[3] > 9)
                return false

            for (i in dx.indices) {
                move(red, blue, Pair(dx[i], dy[i]), visited)
                if (redState && !blueState)
                    return true
                redState = false
                blueState = false
            }
        }
        return false
    }

    fun move(red: IntArray, blue: IntArray, dir: Pair<Int, Int>, visited: Array<Array<BooleanArray>>) {
        val copyGraph = Array(height) { graph[it].copyOf() }
        var (rx, ry, r, rc) = red
        var (bx, by, b, bc) = blue
        var redStopFlag = false
        var blueStopFlag = false
        copyGraph[rx][ry] = 'R'
        copyGraph[bx][by] = 'B'

        while (true) {
            if (copyGraph[rx + dir.first][ry + dir.second] == '#' || copyGraph[rx + dir.first][ry + dir.second] == 'B') {
                redStopFlag = true
            } else {
                if (copyGraph[rx + dir.first][ry + dir.second] == 'O') {
                    redState = true
                    copyGraph[rx][ry] = '.'
                    redStopFlag = true
                } else {
                    copyGraph[rx][ry] = '.'
                    rx += dir.first
                    ry += dir.second
                    copyGraph[rx][ry] = 'R'
                }
            }
            if (copyGraph[bx + dir.first][by + dir.second] == '#' || copyGraph[bx + dir.first][by + dir.second] == 'R') {
                blueStopFlag = true
            } else {
                if (copyGraph[bx + dir.first][by + dir.second] == 'O') {
                    blueState = true
                    copyGraph[bx][by] = '.'
                    blueStopFlag = true
                } else {
                    copyGraph[bx][by] = '.'
                    bx += dir.first
                    by += dir.second
                    copyGraph[bx][by] = 'B'
                }
            }
            if (redStopFlag && blueStopFlag)
                break
            redStopFlag = false
            blueStopFlag = false
        }
//        println("rx: $rx ry: $ry bx: $bx by: $by")
//        println("blueState: $blueState")
//        println("redState: $redState")
        if (blueState || redState)
            return
        if (!visited[rx][ry][r] || !visited[bx][by][b]) {
//            for (i in copyGraph.indices) {
//                for (j in copyGraph[i].indices) {
//                    print("${copyGraph[i][j]} ")
//                }
//                println()
//            }
//            println()
            queue += mutableListOf(intArrayOf(rx, ry, r, rc + 1), intArrayOf(bx, by, b, bc + 1))
            visited[rx][ry][r] = true
            visited[bx][by][b] = true
        }
    }
}

fun main() {
    Boj13459().solution()
}