import kotlin.math.*

class Boj14719 {
    val width: Int
    val height: Int
    val walls: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            height = it[0]
            width = it[1]
        }

        walls = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun solution() {
        var ans = 0
        
        for (i in 1..walls.size - 2) {
            var start = i
            var end = i
            var leftWall = 0
            var rightWall = 0
            while (start > -1) {
                leftWall = max(leftWall, walls[start])
                --start
            }
            while (end < walls.size) {
                rightWall = max(rightWall, walls[end])
                ++end
            }
            val standWall = min(leftWall, rightWall)
            if (standWall > walls[i]) {
                ans += min(leftWall, rightWall) - walls[i]
            }
        }
        println(ans)
    }
}

fun main() {
    Boj14719().solution()
}