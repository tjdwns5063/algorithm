class Boj4803 {
    val n: Int
    val m: Int
    val graph: Array<MutableList<Int>>
    val visited: BooleanArray
    var cycle: Int = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        graph = Array(n+1) { mutableListOf() }
        repeat(m) {
            readln().split(' ').map { it.toInt() }.also {
                graph[it[0]] += it[1]
                graph[it[1]] += it[0]
            }        
        }
        visited = BooleanArray(n+1) { false }
    }

    fun dfs(start: Int, parent: Int): Boolean {
        visited[start] = true
        for (node in graph[start]) {
            if (!visited[node]) {
                if (!dfs(node, start))
                    return false
            }
            else if (parent != node) {
                return false
            }
        }
        return true
    }

    fun solution(time: Int): Int {
        if (n == 0 && m == 0)
            return 1
        
        var ans = 0
        for (i in 1..n) {
            if (visited[i])
                continue
            if (dfs(i, 0))
                ++ans
        }

        if (ans > 1) {
            println("Case $time: A forest of $ans trees.")
        } else if (ans == 1) {
            println("Case $time: There is one tree.")        
        } else {
            println("Case $time: No trees.")
        }
        return 0
    }
}

fun main() {
    var idx = 1
    while (true) {
        if (Boj4803().solution(idx) == 1)
            break
        ++idx
    }
}