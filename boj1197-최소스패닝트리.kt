class Boj1197 {
    lateinit var parent: IntArray

    private fun find(x: Int): Int {
        return if (parent[x] == x) x else find(parent[x]).also { parent[x] = it }
    }

    fun solution() {
        val (v, e) = readln().split(' ').map { it.toInt() }

        val graph = Array(e) {
            val (a,b,c) = readln().split(' ').map { it.toInt() }
            Triple(a-1, b-1, c)
        }.sortedBy { it.third }
//        println(graph.toList())

//        println(graph.toList())
        var answer = 0
        parent = IntArray(v) { it }
        for (node in graph) {
            val x = find(node.first) // 1
            val y = find(node.second) // 2
            if (x != y) {
                if (x < y) parent[y] = x else parent[x] = y
                answer += node.third
            }
        }
        println(parent.toList())
        println(answer)
    }
}

fun main() {
    Boj1197().solution()
}