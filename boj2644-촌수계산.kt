class Boj2644 {
    val nodeCount: Int
    val target1: Int
    val target2: Int
    val edgeCount: Int
    val graph: Array<MutableList<Int>>

    init {
        nodeCount = readln().toInt()
        readln().split(' ').map { it.toInt() }.also {
            target1 = it[0]
            target2 = it[1]
        }
        edgeCount = readln().toInt()
        graph = Array(nodeCount + 1) { mutableListOf() }

        repeat(edgeCount) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1]
                graph[it[1]] += it[0]
            }
        }

//        println(graph.contentDeepToString())
    }

    fun dfs(start: Int, depth: Int, visited: BooleanArray): Int {
//        println("start: $start depth: $depth")
        if (start == target2) {
            return depth
        }
        visited[start] = true
        for (node in graph[start]) {
            if (visited[node])
                continue
            val ret = dfs(node, depth + 1, visited)
            if (ret != -1)
                return ret
        }
        return -1
    }

    fun solution() {
        dfs(target1,0, BooleanArray(nodeCount + 1) { false }).also {
            println(it)
        }
    }
}

fun main() {
    Boj2644().solution()
}