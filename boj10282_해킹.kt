//time = 2sec
//memory = 256mb
//n = 1 <= n <= 10,000
//d = 1 <= d <= 100,000
//time_complex = dlogn

import java.util.*
import kotlin.math.*

class Boj10282 {
    val n: Int
    val d: Int
    val c: Int
    val graph: Array<MutableList<Pair<Int, Int>>>
    val distTable: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            d = it[1]
            c = it[2]
        }
        graph = Array(n+1) { mutableListOf() }
        repeat(d) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[1]] += it[0] to it[2]
            }
        }
        distTable = IntArray(n + 1) { Int.MAX_VALUE }
    }

    fun bfs() {
        val pQ = PriorityQueue<Pair<Int, Int>> { first, second -> first.second - second.second }
        pQ += c to 0
        distTable[c] = 0

        while (pQ.isNotEmpty()) {
            val (curr, currDist) = pQ.poll()
            
            if (distTable[curr] != currDist)
                continue
            for ((next, dist) in graph[curr]) {
                if (distTable[next] > distTable[curr] + dist) {
                    distTable[next] = distTable[curr] + dist
                    pQ += next to distTable[next]
                }
            }
        }
        // println(distTable.contentToString())
    }

    fun solution() {
        // println(graph.contentDeepToString())
        bfs()
        var cnt = 0
        var time = 0
        for (i in 1..n) {
            if (distTable[i] != Int.MAX_VALUE) {
                ++cnt
                time = max(time, distTable[i])
            }
        }
        println("$cnt $time")
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj10282().solution()
    }
}