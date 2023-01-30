import kotlin.math.abs
import kotlin.math.min

class Boj15686 {
    val n: Int
    val m: Int
    val city: Array<IntArray>
    var ans = Int.MAX_VALUE
    val homes = mutableListOf<Pair<Int,Int>>()
    val chickens = mutableListOf<Pair<Int, Int>>()
    val lst = mutableListOf<Int>()

    init {
        readln().split(' ').map { it.toInt() }.also {
            n=it[0]
            m=it[1]
        }
        city = Array(n) { readln().split(' ').map { it.toInt() }.toIntArray() }
        for (i in city.indices) {
            for (j in city[i].indices) {
                if (city[i][j] == 1)
                    homes += Pair(i, j)
                if (city[i][j] == 2)
                    chickens += Pair(i, j)
            }
        }
    }

    fun checkChickenDist(visit: BooleanArray) {
        var chickenDist = 0
        for (i in homes.indices) {
            var dist = Int.MAX_VALUE
            for (j in chickens.indices) {
                if (j !in lst)
                    continue
                val tmp = abs(homes[i].first - chickens[j].first) + abs(homes[i].second - chickens[j].second)
                if (dist > tmp)
                    dist = tmp
            }
            if (dist != Int.MAX_VALUE)
                chickenDist += dist
        }
        ans = min(chickenDist, ans)
    }

    fun dfs(cnt: Int, visit: BooleanArray) {
        if (cnt == m) {
            checkChickenDist(visit)
            return
        }

        for (i in chickens.indices) {
            if (visit[i])
                continue
            visit[i] = true
            lst += i
            dfs(cnt + 1, visit)
            lst.removeLast()
            for (j in i + 1 until visit.size)
                visit[j] = false
        }
    }

    fun solution() {
        dfs(0, BooleanArray(chickens.size) {false})
        println(ans)
    }
}

fun main() {
    Boj15686().solution()
}