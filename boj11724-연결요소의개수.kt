class Boj11724 {
    val nodeCount: Int
    val edgeCount: Int
    val graph: Array<MutableList<Int>>
    val visited: BooleanArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            nodeCount = it[0]
            edgeCount = it[1]
        }
        graph = Array(nodeCount + 1) { mutableListOf() }
        visited = BooleanArray(nodeCount + 1) { false }

        repeat(edgeCount) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1]
                graph[it[1]] += it[0]
            }
        }
    }

    fun solution() {
        val queue = ArrayDeque<Int>()
        var answer = 0

        for (i in 1..nodeCount) {
            if (visited[i])
                continue
            queue += i
            answer++
            while (queue.isNotEmpty()) {
                val curr = queue.first()
                queue.removeFirst()
                visited[curr] = true
                for (node in graph[curr]) {
                    if (visited[node])
                        continue
                    queue += node
                    visited[node] = true
                }
            }
        }
        println(answer)
    }
}

fun main() {
    Boj11724().solution()
}
