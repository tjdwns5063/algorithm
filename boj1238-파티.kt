import java.util.PriorityQueue

class Boj1238 {

    val nodeCount: Int
    val edgeCount: Int
    val atNode: Int
    val graph: Array<MutableList<Pair<Int, Int>>>
    val distTable: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            nodeCount = it[0]
            edgeCount = it[1]
            atNode = it[2]
        }

        graph = Array(nodeCount + 1) { mutableListOf() }

        repeat(edgeCount) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += Pair(it[1], it[2])
            }
        }
        distTable = IntArray(nodeCount + 1) { Int.MAX_VALUE }
    }

    fun backToAt() { // 오는 길 구하기
        distTable[atNode] = 0
        val pQ = PriorityQueue<Pair<Int, Int>> { first, second ->
            first.second - second.second
        }
        pQ += Pair(atNode, 0)
        while (pQ.isNotEmpty()) {
            val (currNode, currDist) = pQ.poll()

            if (currDist != distTable[currNode])
                continue
            for ((node, distance) in graph[currNode]) {
                if (distTable[node] > distTable[currNode] + distance) {
                    distTable[node] = distTable[currNode] + distance
                    pQ += Pair(node, distTable[node])
                }
            }
        }
//        println("goBack: ${distTable.contentToString()}")
    }

    fun goToAt(start: Int): Int { // 가는 길 거리 구하기
        val goDistTable = IntArray(nodeCount + 1) { Int.MAX_VALUE }

        goDistTable[start] = 0

        val pQ = PriorityQueue<Pair<Int, Int>> { first, second ->
            first.second - second.second
        }
        pQ += Pair(start, 0)

        while (pQ.isNotEmpty()) {
            val (currNode, currDist) = pQ.poll()

            if (currDist != goDistTable[currNode])
                continue
            for ((node, distance) in graph[currNode]) {
                if (goDistTable[node] > goDistTable[currNode] + distance) {
                    goDistTable[node] = goDistTable[currNode] + distance
                    pQ += Pair(node, goDistTable[node])
                }
            }
        }
//        println("goTo: ${goDistTable.contentToString()}")
        return distTable[start] + goDistTable[atNode]
    }

    fun solution() {
        val answer = mutableListOf<Int>()

        backToAt()
        for (i in 1..nodeCount) {
            answer += goToAt(i)
        }
//        println(answer)
        println(answer.maxOf { it })
    }
}

fun main() {
    Boj1238().solution()
}