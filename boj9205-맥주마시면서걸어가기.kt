import kotlin.math.abs

class Boj9205 {
    val storeCnt: Int
    val homeCoord: Pair<Int, Int>
    val storeCoords: MutableList<Pair<Int, Int>>
    val visited: BooleanArray
    val festivalCoord: Pair<Int, Int>
    var flag = false

    init {
        storeCnt = readln().toInt()
        homeCoord = readln().split(' ').map { it.toInt() }.let {
            it[0] to it[1]
        }
        storeCoords = mutableListOf()
        repeat(storeCnt) {
            storeCoords += readln().split(' ').map { it.toInt() }.let {
                it[0] to it[1]
            }
        }
        festivalCoord = readln().split(' ').map { it.toInt() }.let {
            it[0] to it[1]
        }

        visited = BooleanArray(storeCnt) { false }
    }

    fun getDist(one: Pair<Int, Int>, two: Pair<Int, Int>): Int {
        return abs(one.first - two.first) + abs(one.second - two.second)
    }

    fun dfs(currCoord: Pair<Int, Int>) {
        if (getDist(currCoord, festivalCoord) <= 1000) {
//            println("heres")
            flag = true
            return
        }
        for (i in storeCoords.indices) {
            if (visited[i])
                continue
//            println("curr: $currCoord")
//            println("stroe: ${storeCoords[i]}")
//            println("dist: ${getDist(currCoord, storeCoords[i])}")
            if (getDist(currCoord, storeCoords[i]) <= 1000) {
                visited[i] = true
                dfs(storeCoords[i])
            }
        }
    }

    fun solution() {
        dfs(homeCoord)
        println(if (flag) "happy" else "sad")
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj9205().solution()
    }
}