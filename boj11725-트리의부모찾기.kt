import kotlin.math.min

class Boj11725 {
    val nodeCount: Int = readln().toInt()
    val graph: Array<MutableList<Int>> = Array(nodeCount + 1) { mutableListOf() }
    val heights = Array(100001) { 0 }

    init {
        repeat(nodeCount - 1) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1]
                graph[it[1]] += it[0]
            }
        }
//        println(graph.contentToString())
    }

    fun dfs(start: Int, height: Int, visited: BooleanArray) {
        visited[start] = true
//        print("$start ")

        heights[start] = height
        for (node in graph[start]) {
            if (visited[node])
                continue
//            heights += Pair(start, height)
            dfs(node, height + 1, visited)
        }
    }

    fun solution() {
        dfs(1,1, BooleanArray(nodeCount + 1) { false })

       graph.forEachIndexed { idx, value ->
           value.sortedWith { o1, o2 ->
               heights[o1] - heights[o2]
           }.also {
               if (idx > 1) println(it[0])
           }
       }
    }
}

fun main() {
    Boj11725().solution()
}