import kotlin.math.*

class Boj15683 {
    val n: Int
    val m: Int
    val board: Array<IntArray>
    val cameraCoord: MutableList<Pair<Int, Int>>
    val cameras: MutableList<Int>
    var ans = Int.MAX_VALUE

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        board = Array(n) { readln().split(' ').map { it.toInt() }.toIntArray() }
        cameraCoord = mutableListOf()
        cameras = mutableListOf()
    }

    fun seeRight(copy: Array<IntArray>, coord: Pair<Int, Int>) {
        for (j in coord.second until m) {
            if (copy[coord.first][j] == 6)
                break
            if (copy[coord.first][j] == 0)
                copy[coord.first][j] = 7
        }
    }

    fun seeLeft(copy: Array<IntArray>, coord: Pair<Int, Int>) {
        for (j in coord.second downTo 0) {
            if (copy[coord.first][j] == 6)
                break
            if (copy[coord.first][j] == 0)
                copy[coord.first][j] = 7
        }
    }
 
    fun seeUp(copy: Array<IntArray>, coord: Pair<Int, Int>) {
        for (i in coord.first downTo 0) {
            if (copy[i][coord.second] == 6)
                break
            if (copy[i][coord.second] == 0)
                copy[i][coord.second] = 7
        }
    }

    fun seeDown(copy: Array<IntArray>, coord: Pair<Int, Int>) {
        for (i in coord.first until n) {
            if (copy[i][coord.second] == 6)
                break
            if (copy[i][coord.second] == 0)
                copy[i][coord.second] = 7
        }
    }

    fun camera1(copy: Array<IntArray>, coord: Pair<Int, Int>, dir: Int) {
        if (dir == 0) {
            seeRight(copy, coord)
        } else if (dir == 1) {
            seeDown(copy, coord)
        } else if (dir == 2) {
            seeLeft(copy, coord)
        } else if (dir == 3) {
            seeUp(copy, coord)
        }

        // for (i in copy.indices) {
        //     for (j in copy[i].indices) {
        //         print("${copy[i][j]} ")
        //     }
        //     println()
        // }
    }

    fun camera2(copy: Array<IntArray>, coord: Pair<Int, Int>, dir: Int) {
        if (dir == 0 || dir == 2) {
            seeRight(copy, coord)
            seeLeft(copy, coord)
        } else if (dir == 1 || dir == 3) {
            seeUp(copy, coord)
            seeDown(copy, coord)
        }
    }

    fun camera3(copy: Array<IntArray>, coord: Pair<Int, Int>, dir: Int) {
        if (dir == 0) {
            seeRight(copy, coord)
            seeUp(copy, coord)
        } else if (dir == 1) {
            seeRight(copy, coord)
            seeDown(copy, coord)
        } else if (dir == 2) {
            seeLeft(copy, coord)
            seeDown(copy, coord)
        } else if (dir == 3) {
            seeLeft(copy, coord)
            seeUp(copy, coord)
        }
    }

    fun camera4(copy: Array<IntArray>, coord: Pair<Int, Int>, dir: Int) {
        if (dir == 0) {
            seeUp(copy, coord)
            seeLeft(copy, coord)
            seeRight(copy, coord)
        } else if (dir == 1) {
            seeUp(copy, coord)
            seeRight(copy,coord)
            seeDown(copy, coord)
        } else if (dir == 2) {
            seeRight(copy, coord)
            seeDown(copy, coord)
            seeLeft(copy, coord)
        } else if (dir == 3) {
            seeLeft(copy, coord)
            seeUp(copy, coord)
            seeDown(copy, coord)
        }
    }

    fun camera5(copy: Array<IntArray>, coord: Pair<Int, Int>) {
        seeUp(copy, coord)
        seeDown(copy, coord)
        seeLeft(copy, coord)
        seeRight(copy, coord)       
    }

    fun simulate() {
        val copyBoard = Array(n) { IntArray(m) { 0 } }
        for (i in 0 until n) {
            for (j in 0 until m) {
                copyBoard[i][j] = board[i][j]
            }
        }

        for (i in cameraCoord.indices) {
            val (x, y) = cameraCoord[i]

            when (copyBoard[x][y]) {
                1 -> camera1(copyBoard, cameraCoord[i], cameras[i])
                2 -> camera2(copyBoard, cameraCoord[i], cameras[i])
                3 -> camera3(copyBoard, cameraCoord[i], cameras[i])
                4 -> camera4(copyBoard, cameraCoord[i], cameras[i])
                5 -> camera5(copyBoard, cameraCoord[i])
                else -> {}
            }
        }
        var cnt = 0
        for (i in copyBoard.indices) {
            for (j in copyBoard[i].indices) {
                // print("${copyBoard[i][j]} ")
                if (copyBoard[i][j] == 0)
                    ++cnt
            }
            // println()
        }
        // println()
        ans = min(ans, cnt)
    }

    fun dfs(depth: Int) {
        if (depth >= cameraCoord.size) {
            // println(cameras)
            simulate()
            return
        }
        for (i in 0 until 4) {
            cameras += i
            dfs(depth + 1)
            cameras.removeLast()
        }
    }

    fun solution() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] != 0 && board[i][j] != 6) {
                    cameraCoord += i to j
                }
            }
        }
        dfs(0)
        println(ans)
    }
}

fun main() {
    Boj15683().solution()
}