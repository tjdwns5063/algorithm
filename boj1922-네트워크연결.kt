class Boj1922 {
    private fun find(parent: Array<Int>, x: Int): Int  {
        return if (x==parent[x]) x else find(parent, parent[x]).also { parent[x] = it }
    }

    fun solution() {
        val nodeCnt = readln().toInt()
        val edgeCnt = readln().toInt()

        val edges = Array(edgeCnt) {
            val (a,b,c) = readln().split(' ').map { it.toInt() }
            Triple(a-1,b-1,c)
        }.sortedBy { it.third }
//        println(edges.toList())

        val parent = Array(nodeCnt) { it }
        var answer = 0
        for (edge in edges) {
            val x = find(parent, edge.first)
            val y = find(parent, edge.second)
            if (x != y) {
//                println(edge)
                if (x < y) parent[y] = x else parent[x] = y
                answer += edge.third
            }
        }
        println(answer)
    }
}

fun main() {
    Boj1922().solution()
}