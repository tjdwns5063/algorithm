import kotlin.math.min

class Boj11404 {
    private fun makeTable(nodeCnt: Int, edges: Array<IntArray>, inf: Int): Array<IntArray> {
        val table = Array(nodeCnt) { IntArray(nodeCnt) { inf } }
        for (edge in edges) {
            table[edge[0] - 1][edge[1] - 1] = min(table[edge[0]-1][edge[1]-1], edge[2])
        }
        for (i in table.indices) {
            table[i][i] = 0
        }
        return table
    }

    fun solution() {
        val nodeCnt = readln().toInt()
        val edgeCnt = readln().toInt()
        val inf = (99 * 110000)

        val edges = Array(edgeCnt) {
            val (a, b, c) = readln().split(' ').map { it.toInt() }
            intArrayOf(a,b,c)
        }

        val table = makeTable(nodeCnt, edges, inf)

        for (k in table.indices) {
            for (i in table.indices) {
                for (j in table.indices) {
                    table[i][j] = min(table[i][j], table[i][k] + table[k][j])
                }
            }
        }

        for (i in table.indices) {
            for (j in table.indices) {
                if (table[i][j] == inf) {
                    print("0 ")
                } else {
                    print("${table[i][j]} ")
                }
            }
            println()
        }
    }
}

fun main() {
    Boj11404().solution()
}