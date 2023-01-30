import java.util.PriorityQueue

const val inf = 4_000_000

class Boj1753 {
    fun printAnswer(dist: Array<Int>) {
        for (i in 1 until dist.size) {
            println(if (dist[i] != inf) dist[i] else "INF")
        }
    }

    fun solution() {
        val (nodeCnt, edgeCnt) = readln().split(' ').map { it.toInt() }
        val start = readln().toInt()
        val graph = Array(nodeCnt + 1) { arrayListOf<Pair<Int, Int>>() }

        repeat(edgeCnt) {
            val input = readln().split(' ').map { it.toInt() }
            graph[input[0]] += Pair(input[1], input[2])
        }
        val dist = Array(nodeCnt + 1) { inf }
        // 1. 최단거리 테이블을 모두 inf로 초기화

//        println(graph.contentToString())

        dist[start] = 0
        // 3. 시작노드의 최단거리를 0으로 설정.
        val heap = PriorityQueue { a: Pair<Int, Int>, b: Pair<Int, Int> ->
            if (a.first > b.first) 1 else if (a.first == b.first) 0 else -1
        }
        heap += Pair(0, start)
        while (heap.isNotEmpty()) {
            val (distance, node) = heap.poll()
            /*
             * 4. 최단거리 테이블을 순회하면서 아직 방문하지
             * 않은 최단거리로 도달할 수 있는 노드를 선택합니다.
             */
            if (dist[node] != distance)
                continue
            /*
         	* 5. 선택된 노드를 방문 표시하고,
         	* 그 노드의 간선들을 순회하며 최단거리 테이블을 갱신합니다.
         	*/
            for (edge in graph[node]) {
                if (dist[edge.first] > dist[node] + edge.second) {
                    dist[edge.first] = dist[node] + edge.second
                    heap += Pair(dist[edge.first], edge.first)
                }
            }
            // 6. 4~5번을 더 이상 선택할 노드가 없을 때까지 반복합니다.
        }
        printAnswer(dist)
    }
}

fun main() {
    Boj1753().solution()
}