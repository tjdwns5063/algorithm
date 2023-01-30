import java.util.*

class Boj5972 {
    val nodeCount: Int
    val edgeCount: Int
    val graph: Array<MutableList<Pair<Int, Int>>>

    init {
        readln().split(' ').map{ it.toInt() }.also {
            nodeCount = it[0]
            edgeCount = it[1]
        }
        graph = Array(nodeCount + 1) { mutableListOf() }
        repeat(edgeCount) {
            readln().split(' ').map {it.toInt()}.also {
                graph[it[0]] += Pair(it[1], it[2])
                graph[it[1]] += Pair(it[0], it[2])
            }
        }
        // println(graph.contentToString())
    }

    fun dijkstra() {
        val distTable = IntArray(nodeCount + 1) { Int.MAX_VALUE }
        val pQ = PriorityQueue<Pair<Int, Int>> { first, second ->
            first.second - second.second
        }
        distTable[1] = 0
        pQ += 1 to distTable[1]

        while (pQ.isNotEmpty()) {
            val (curr, currDist) = pQ.poll()

            if (distTable[curr] != currDist)
                continue
            for ((next, nextDist) in graph[curr]) {
                if (distTable[next] > distTable[curr] + nextDist) {
                    distTable[next] = distTable[curr] + nextDist
                    pQ += next to distTable[next]
                }
            }
        }
        println(distTable[nodeCount])
    }

    fun solution() {
        dijkstra()
    }
}

fun main() {
    Boj5972().solution()
}