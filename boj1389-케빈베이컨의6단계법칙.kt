import kotlin.math.min

class Boj1389 {
    val nodeCount: Int
    val edgeCount: Int
    val graph: Array<MutableList<Int>>
    var sum = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            nodeCount = it[0]
            edgeCount = it[1]
        }

        graph = Array(nodeCount + 1) { mutableListOf() }

        repeat(edgeCount) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1]
                graph[it[1]] += it[0]
            }
        }

//        println(graph.contentDeepToString())
    }

    fun bfs(start: Int, target: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = BooleanArray(nodeCount + 1) { false }

        queue += Pair(start, 0)
        while (queue.isNotEmpty()) {
            val curr = queue.first()
            visited[curr.first] = true
            queue.removeFirst()
            if (curr.first == target) {
//                println("height: ${curr.second}")
                return curr.second
            }
            for (node in graph[curr.first]) {
                if (visited[node])
                    continue
                queue += Pair(node, curr.second + 1)
                visited[node] = true
            }
        }
        return 0
    }

    fun solution() {
        val sumList = mutableListOf<Int>()
        for (i in 1..nodeCount) {
            var sum = 0
            for (j in 1..nodeCount) {
                if (i == j)
                    continue
                sum += bfs(i, j)
            }
            sumList += sum
        }
        println(sumList.indexOf(sumList.minOf { it }) + 1)
    }
}

fun main() {
    Boj1389().solution()
}