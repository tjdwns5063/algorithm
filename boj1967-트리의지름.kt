import kotlin.math.max

class Boj1967 {
    val nodeCount: Int
    val graph: Array<MutableList<Pair<Int, Int>>>
    val visited: BooleanArray
    var ans: Int = 0
    var node: Int = 0

    init {
        nodeCount = readln().toInt()
        graph = Array(nodeCount + 1) { mutableListOf() }
        visited = BooleanArray(nodeCount + 1) { false }

        repeat(nodeCount - 1) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += Pair(it[1], it[2])
                graph[it[1]] += Pair(it[0], it[2])
            }
        }
    }

    fun solution() {
        dfs(1, 0)
//        println("node: $node")
        for (i in visited.indices) {
            visited[i] = false
        }
        dfs(node, 0)
//        println("node: $node")
        println(ans)
    }

    fun dfs(start: Int, len: Int) {
        if (visited[start])
            return
        visited[start] = true
        if (ans < len) {
            ans = len
            node = start
        }

        for (node in graph[start]) {
            if (visited[node.first])
                continue
            dfs(node.first, len + node.second)
        }
    }
}

fun main() {
    Boj1967().solution()
}