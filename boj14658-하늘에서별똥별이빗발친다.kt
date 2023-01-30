import kotlin.math.min

class Boj14658 {
    val height: Int
    val width: Int
    val tramSize: Int
    val starCount: Int
    val starList: MutableList<Pair<Int, Int>>

    init {
        readln().split(' ').map { it.toInt() }.also {
            width = it[0]
            height = it[1]
            tramSize = it[2]
            starCount = it[3]
        }

        starList = mutableListOf()

        repeat(starCount) {
            val (y, x) = readln().split(' ').map { it.toInt() }
            starList += Pair(y, x)
        }
    }

    fun solution() {
        var ans = Int.MAX_VALUE

        for (i in starList.indices) {
            for (j in starList.indices) {
                val x = starList[i].first
                val y = starList[j].second
                val nx = x + tramSize
                val ny = y + tramSize

                val cnt = starList.fold(0) { acc, star ->
                    if (star.first in x..nx && star.second in y..ny)
                        acc + 1
                    else
                        acc
                }
                ans = min(starList.size - cnt, ans)
            }
        }
        println(ans)
    }
}

fun main() {
    Boj14658().solution()
}