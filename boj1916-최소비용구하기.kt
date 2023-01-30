import java.util.PriorityQueue

class Boj1916 {
    private val inf = 100_000L * 100_100L
    fun solution() {
        val nodeCnt = readln().toInt()
        val edgeCnt = readln().toInt()
        val graph = Array(nodeCnt + 1) { arrayListOf<Pair<Long, Long>>() }
        repeat(edgeCnt) {
            val input = readln().split(' ').map { it.toLong() }
            graph[input[0].toInt()] += Pair(input[1], input[2])
        }
        val (start, end) = readln().split(' ').map { it.toInt() }
        val distTable = Array(nodeCnt + 1) { inf }
        val priorityQ = PriorityQueue { a: Pair<Long, Long>, b: Pair<Long, Long> ->
            if (a.first > b.first) 1 else if (a.first == b.first) 0 else -1
        }
        distTable[start] = 0
        priorityQ += Pair(start.toLong(), 0)
        while (priorityQ.isNotEmpty()) {
            val curr = priorityQ.poll()
            if (curr.second != distTable[curr.first.toInt()])
                continue
            for (edge in graph[curr.first.toInt()]) {
                if (distTable[edge.first.toInt()] > distTable[curr.first.toInt()] + edge.second) {
                    distTable[edge.first.toInt()] = distTable[curr.first.toInt()] + edge.second
                    priorityQ += Pair(edge.first, distTable[edge.first.toInt()])
                }
            }
        }
        println(distTable[end])
    }
}

fun main() {
    Boj1916().solution()
}