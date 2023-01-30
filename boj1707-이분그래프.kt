class Boj1707 {
    val nodeCount: Int
    val edgeCount: Int
    val graph: Array<MutableList<Int>>
    val checker: IntArray
    var ans = true

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

        checker = IntArray(nodeCount + 1) { 0 }
    }

    fun dfs(start: Int, color: Int) {
        checker[start] = color

        for (node in graph[start]) {
            if (checker[node] == color) {
                ans = false
                return
            }
            if (checker[node] == 0)
                dfs(node, -color)
        }
    }

    fun solution() {
        for (i in 1..nodeCount) {
            if (!ans)
                break

            if (checker[i] == 0)
                dfs(i, 1)
        }
        println(if (ans) "YES" else "NO")
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj1707().solution()
    }
}