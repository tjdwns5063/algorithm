import java.util.PriorityQueue

class ShortestPathProblem
{
    fun FloydWarshall(node: Int, graph: Array<IntArray>) {
        // 1. 최단거리 테이블을 inf로 초기화.
        val shortestPath = Array(node + 1) { Array(node + 1) { 1000000 } } // 최단거리 테이블
        val routeTable = Array(node + 1) { Array(node + 1) { -1 } }

        /* 2. 그래프에서 다른 정점을 거치지 않고 바로 갈 수 있는 경우를
         *    최단 거리 테이블에 입력.
         */
        graph.forEach { arr ->
            shortestPath[arr[0]][arr[1]] = arr[2]
            shortestPath[arr[1]][arr[0]] = arr[2]
            routeTable[arr[0]][arr[1]] = arr[1]
            routeTable[arr[1]][arr[0]] = arr[0]
        }
        // 3. 최단거리 테이블에서 자기 자신으로 가는 거리를 0으로 변경.
        for (i in 1..node) {
            for (j in 1..node) {
                if (i == j)
                    shortestPath[i][j] = 0
            }
        }
        shortestPath.forEach { println(it.toList()) }
        println()
        routeTable.forEach { println(it.toList()) }
        println()

        /* 4. 삼중 for문을 사용하여 각 k번째 노드를 거쳐서 한 정점에서 다른 정점으로
         *    갈 수 있는 모든 경우의 수를 고려하여 최단거리 테이블 갱신.
         */
        for (k in 1..node) {
            for (i in 1..node) {
                for (j in 1..node) {
                    if (shortestPath[i][j] > shortestPath[i][k] + shortestPath[k][j]) {
                        routeTable[i][j] = routeTable[i][k]
                        shortestPath[i][j] = shortestPath[i][k] + shortestPath[k][j]
                    }
                }
            }
        }
        routeTable.forEach { println(it.toList()) }
        println()

        // routeTable을 이용한 경로 복원. (2->3으로 가는 최단경로는 2->4->3)
        var from = 1
        var to = 4
        for (i in 1..node) {
            for (j in 1..node) {
                if (from == to) {
                    print("$from ")
                    return
                }
                print("$from ")
                from = routeTable[from][to]
            }
        }
    }

    fun dijkstraNative(node: Int, start: Int, graph: Array<Array<Pair<Int, Int>>>) {
//        println(graph.contentDeepToString())
        val distTable = Array(node + 1) { inf }
        val visit = Array(node + 1) { false }

        distTable[start] = 0
        while (true) {
            var idx = 0
            for (i in 1..node) {
                if (visit[i])
                    continue
                if (distTable[idx] > distTable[i])
                    idx = i
            }
            println("idx: $idx")
            if (distTable[idx] == inf)
                break
            visit[idx] = true
            for (edge in graph[idx]) {
                if (distTable[edge.first] > distTable[idx] + edge.second) {
                    distTable[edge.first] = distTable[idx] + edge.second
                }
                println(distTable.contentToString())
            }
        }
        distTable.forEach { println(it) }
    }

    fun dijkstraFast(nodeCnt: Int, start:Int, graph: Array<Array<Pair<Int, Int>>>) {
        val distTable = Array(nodeCnt + 1) { inf }
        val priorityQ = PriorityQueue { a: Pair<Int, Int>, b: Pair<Int, Int> ->
            if (a.first > b.first) 1 else if (a.first == b.first) 0 else -1
        }

        distTable[start] = 0
        priorityQ += Pair(0, start) // first: distance, second: node
        while (priorityQ.isNotEmpty()) {
            val (dist, node) = priorityQ.poll()
            if (dist != distTable[node])
                continue
            for (edge in graph[node]) {
                if (distTable[edge.first] > distTable[node] + edge.second) {
                    distTable[edge.first] = distTable[node] + edge.second
                    priorityQ += Pair(edge.second, edge.first)
                }
            }
        }
        distTable.forEach { println(it) }
    }
}

fun main() {
//    val graph = arrayOf(
//        intArrayOf(1,2,4), intArrayOf(1,4,1), intArrayOf(1,3,1), intArrayOf(3,4,3),
//        intArrayOf(4,5,6), intArrayOf(3,5,15), intArrayOf(2,5,8)
//    )

    val graph = arrayOf(
        intArrayOf(1,2,4), intArrayOf(1,3,2), intArrayOf(2,3,5), intArrayOf(2,4,1),
        intArrayOf(3,4,2)
    )
    ShortestPathProblem().FloydWarshall(4, graph)

//    ShortestPathProblem().dijkstraNative(5, 1,
//        arrayOf(
//            arrayOf(),
//            arrayOf(Pair(2, 2), Pair(3, 3)),
//            arrayOf(Pair(3,4), Pair(4,5)),
//            arrayOf(Pair(4,6)),
//            arrayOf(),
//            arrayOf(Pair(1,1))
//        ))
//
//    ShortestPathProblem().dijkstraFast(5, 1,
//        arrayOf(
//            arrayOf(),
//            arrayOf(Pair(2, 2), Pair(3, 3)),
//            arrayOf(Pair(3,4), Pair(4,5)),
//            arrayOf(Pair(4,6)),
//            arrayOf(),
//            arrayOf(Pair(1,1))
//        ))
}