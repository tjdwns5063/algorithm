class Boj1976 {
    val nodeCount: Int
    val planCount: Int
    val graph: Array<MutableList<Int>>
    val planList: List<Int>
    val visited: BooleanArray

    init {
        nodeCount = readln().toInt()
        planCount = readln().toInt()

        graph = Array(nodeCount + 1) { mutableListOf() }
        repeat (nodeCount) {
            readln().split(' ').map {it.toInt()}.forEachIndexed { idx, value -> 
                if (value == 1) {
                    graph[idx + 1] += it + 1
                    // graph[it + 1] += idx + 1
                }
            }
        }
        planList = readln().split(' ').map { it.toInt() }
        visited = BooleanArray(nodeCount + 1) { false }
        // println(graph.contentToString())
    }

    fun dfs(start: Int) {
        if (visited[start])
            return
        visited[start] = true
        for (node in graph[start]) {
            dfs(node)
        }
    }

    fun solution() {
        dfs(planList[0])
        for (plan in planList) {
            if (!visited[plan]) {
                println("NO")
                return
            }
        }
        println("YES")
    }
}

fun main() {
    Boj1976().solution()
}