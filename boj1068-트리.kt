class Boj1068 {
    val nodeCount: Int
    val graph: Array<MutableList<Int>>
    val parents: List<Int>
    val visited: BooleanArray
    var root: Int = 0
    val target: Int
    var ans = 0

    init {
        nodeCount = readln().toInt()
        graph = Array(nodeCount) { mutableListOf() }
        visited = BooleanArray(nodeCount) { false }
        parents = readln().split(' ').map { it.toInt() }
        parents.forEachIndexed { idx, value ->
            if (value == -1) {
                root = idx
            } else {
                graph[value] += idx
                graph[idx] += value
            }
        }
        target = readln().toInt()
    }

    fun solution() {
        if (target != root) {
            visited[parents[target]] = true
        }
        checkRemove(target)
        if (target != root) {
            visited[parents[target]] = false
        }
//        println(visited.contentToString())
//        println(parents)
        val newGraph = Array(nodeCount) { mutableListOf<Int>() }
        parents.forEachIndexed { idx, value ->
            if (!visited[idx] && value != -1) {
                newGraph[value] += idx
                newGraph[idx] += value
            }
        }
//        newGraph.forEach { print("$it ") }
//        println(visited.contentToString())
        findLeaf(newGraph)
        println(ans)
    }

    fun checkRemove(start: Int) {
        if (visited[start])
            return
        visited[start] = true
        for (node in graph[start]) {
            checkRemove(node)
        }
    }

    private fun findLeaf(newGraph: Array<MutableList<Int>>) {
        for ((idx,node) in newGraph.withIndex()) {
            if (idx == root && node.size == 0 && !visited[idx])
                ans += 1
            if (idx != root && node.size == 1)
                ans += 1
        }
    }
}

fun main() {
    Boj1068().solution()
}