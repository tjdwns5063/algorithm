import java.util.*

class Boj1446 {
    val n: Int
    val len: Int
    val distTable: IntArray
    val graph: Array<MutableList<Pair<Int, Int>>>

    init {
        readln().split(' ').map {it.toInt()}.also {
            n = it[0]
            len = it[1]
        }

        graph = Array(10005) { mutableListOf() }
        repeat(n) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1] to it[2]
            }
        }

        for (i in graph.indices) {
            graph[i] += i + 1 to 1
        }
        
        distTable = IntArray(10005) { Int.MAX_VALUE }
    }

    fun dijkstra() {
        val pQ = PriorityQueue<Pair<Int, Int>> { first, second -> first.second - second.second }
        pQ += 0 to 0
        distTable[0] = 0

        while (pQ.isNotEmpty()) {
            val (curr, currDist) = pQ.poll()

            if (curr > 10000)
                break
            if (distTable[curr] != currDist)
                continue
            for ((next, nextDist) in graph[curr]) {
                if (distTable[next] > distTable[curr] + nextDist) {
                    distTable[next] = distTable[curr] + nextDist
                    pQ += next to distTable[next]
                }
            }
        }
        println(distTable[len])
    }

    fun solution() {
        // println(graph.contentToString())
        dijkstra()
    }
}

fun main() {
    Boj1446().solution()
}