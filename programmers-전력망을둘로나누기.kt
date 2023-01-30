import kotlin.math.abs
import kotlin.math.min

class DivideWires {
    var total = 0
    fun dfs(graph: Array<MutableList<Int>>, visited: Array<Boolean>, start: Int):Int {
        visited[start] = true
        ++total
        for (i in graph[start]) {
            if (!visited[i]) {
                dfs(graph, visited, i)
            }
        }
        return start
    }

    fun makeGraph(n: Int, vertex: Int, wires: Array<IntArray>): Array<MutableList<Int>> {
        val graph = Array(n) { mutableListOf<Int>() }

        for (i in wires.indices) {
            if (i == vertex)
                continue
            graph[wires[i][0] - 1] += wires[i][1] - 1
            graph[wires[i][1] - 1] += wires[i][0] - 1
        }
//        graph.forEach { println(it) }
        return graph
    }

    fun solution(n: Int, wires: Array<IntArray>): Int {
//        wires.forEach { println(it.toList()) }
        var answer = n
        for (i in 0 until n) {
            val cutted_graph = makeGraph(n, i, wires) // 각 버텍스를 자른 그래프 생성
//            println("i: $i")
            val visited = Array(n) { false }
            dfs(cutted_graph, visited, 0) // 순회하면서 비교
            val left_total = total
            total = 0
            var right = 0
            visited.forEachIndexed { idx, item ->
                if (!item) {
                    right = idx
                    return@forEachIndexed
                }
            }
//            println()
            dfs(cutted_graph, visited, right)
            val right_total = total
            total = 0
            answer = min(abs(left_total - right_total), answer)
        }
//        println("answer:$answer") // 답 제출
        return answer
    }
}

fun main() {
    val wires = arrayOf(
        intArrayOf(1,2), intArrayOf(2,7), intArrayOf(3,7), intArrayOf(3,4), intArrayOf(4,5),
        intArrayOf(6,7)
    )
    DivideWires().solution(7, wires).also { println(it) }
}
