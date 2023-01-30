class Boj11403 {
    fun solution() {
        val nodeCnt = readln().toInt()
        val graph = arrayListOf<IntArray>()
        repeat(nodeCnt) {
            graph += readln().split(' ').map { it.toInt() }.toIntArray()
        }

        val table = Array(nodeCnt) { IntArray(nodeCnt) {0} }
        for (i in graph.indices) {
            for (j in graph.indices) {
                table[i][j] = graph[i][j]
            }
        }

        for (k in table.indices) {
            for (i in table.indices) {
                for (j in table.indices) {
                    if (table[i][j] == 0)
                        table[i][j] = if (table[i][k] != 0 && table[k][j] != 0) 1 else 0
                }
            }
        }
        for (i in table.indices) {
            for (j in table.indices) {
                print("${table[i][j]} ")
            }
            println()
        }
    }
}

fun main() {
    Boj11403().solution()
}