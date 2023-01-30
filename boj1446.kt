import java.util.*

class Boj1446 {
    val edgeCount: Int
    val distance: Int
    val edgeList: MutableList<Triple<Int, Int, Int>>
    val distTable: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            edgeCount = it[0]
            distance = it[1]
        }

        edgeList = mutableListOf<Triple<Int, Int, Int>>()
        repeat(edgeCount) {
            edgeList += readln().split(' ').map { it.toInt() }.let {
                Triple(it[0], it[1], it[2])
            }
        }
        distTable = IntArray(10004) { it }
        edgeList.forEach {
            if (distTable[it.second] > it.third)
                distTable[it.second] = it.third
        }
        println(edgeList)
    }

    fun dp() {
        var currStart = edgeList[0].first
        var len = edgeList[0].first
        for (i in edgeList.indices) {
            val (start, end, _) = edgeList[i]
            if (start == currStart && end <= distance) {
                len += distTable[end]
                currStart = end
            }
        }
        println("curr: $currStart len: $len")
        println(distance - currStart + len)
    }

    fun solution() {
        dp()
    }
}

fun main() {
    Boj1446().solution()
}