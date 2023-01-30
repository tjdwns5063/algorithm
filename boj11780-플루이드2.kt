class Boj11780 {
    val inf = 100 * 1000000
    fun printAnswer(nodeCnt: Int, shortestPath: Array<IntArray>, routeTable:Array<IntArray>) {
                for (i in 1..nodeCnt) {
            for (j in 1..nodeCnt) {
                if (shortestPath[i][j] == inf)
                    print("0 ")
                else
                    print("${shortestPath[i][j]} ")
            }
            println()
        }
        for (i in 1..nodeCnt) {
            for (j in 1..nodeCnt) {
                var from = i
                var to = j
                val routeList = arrayListOf<Int>()
                if (routeTable[i][j] == 0)
                    print(0)
                else {
                    while (true) {
//                        println("[to][from]:${routeTable[to][from]}, to: $to")
                        routeList += from
                        from = routeTable[from][to]
                        if (routeTable[from][to] == 0) {
                            break
                        }
                    }
                    routeList += from
                    print("${routeList.size} ")
                    routeList.forEach { print("$it ") }
                }
                println()
            }
         }
    }

    fun solution() {
        val nodeCnt = readln().toInt()
        val edgeCnt = readln().toInt()
        val edges = Array(edgeCnt) {
            val (a,b,c) = readln().split(' ').map { it.toInt() }
            intArrayOf(a,b,c)
        }
        val shortestPath = Array(nodeCnt + 1) { IntArray(nodeCnt + 1) { inf } }
        val routeTable = Array(nodeCnt + 1) { IntArray(nodeCnt + 1) { 0 } }

        for (edge in edges) {
            if (shortestPath[edge[0]][edge[1]] > edge[2])
                shortestPath[edge[0]][edge[1]] = edge[2]
            routeTable[edge[0]][edge[1]] = edge[1]
        }

        for (idx in 1..nodeCnt) {
            shortestPath[idx][idx] = 0
            routeTable[idx][idx] = 0
        }
        println("shortestLength:")
        shortestPath.forEach { println(it.toList()) }

        println("routeTable:")
        routeTable.forEach { println(it.toList()) }


        for (k in 1..nodeCnt) {
            for (i in 1..nodeCnt) {
                for (j in 1..nodeCnt) {
                    if (shortestPath[i][j] > shortestPath[i][k] + shortestPath[k][j]) {
                        shortestPath[i][j] = shortestPath[i][k] + shortestPath[k][j]
                        routeTable[i][j] = routeTable[i][k]
                    }
                }
            }
            shortestPath.forEach { println(it.toList()) }
            println()
        }
//        printAnswer(nodeCnt, shortestPath, routeTable)
    }
}

fun main() {
    Boj11780().solution()
}