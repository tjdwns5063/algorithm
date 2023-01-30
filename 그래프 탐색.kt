class BfsDfs {
    val input = readln().split(' ').map { it.toInt() }
    val graph = Array(input[0] + 1) { arrayListOf<Int>() }
    init {
        repeat(input[1]) {
            val (to, from) = readln().split(' ').map { it.toInt() }
            graph[to] += from
            graph[from] += to
        }
    }

    fun bfs() {
        val nodeCnt = input[0]
        val start = input[2]
        val visit = Array(nodeCnt + 1) { false }

        graph.forEach {
            it.sort()
        }
        val queue = ArrayDeque<Int>()
        queue += start
        while (queue.isNotEmpty()) {
            val currNode = queue.first()
            print("$currNode ")
            queue.removeFirst()
            visit[currNode] = true
            for (i in graph[currNode]) {
                if (!visit[i]) {
                    queue += i
                    visit[i] = true
                }
            }
        }
    }

    fun dfs() {
        val start = input[2]
        val visit = arrayListOf<Int>()

        graph.forEach {
            it.sort()
        }
//        graph.forEach { println(it) }
        val stk = ArrayDeque<Int>()
        stk += start
        while (stk.isNotEmpty()) {
            val currNode = stk.last()
            stk.removeLast()
            if (currNode !in visit) {
                visit += currNode
                for (i in graph[currNode].reversed()) { // 연결된 노드중 작은 노드부터 탐색하기 위해 뒤집어줌.
                    stk += i
                }
            }
//            println("stk: $stk")
//            println("visit: $visit")
        }
        visit.forEach {
            print("$it ")
        }
        println()
    }
}

fun main() {
    val sol = BfsDfs()
    sol.dfs()
    sol.bfs()
}