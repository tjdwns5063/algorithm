import java.util.Queue

class Boj2606 {
    val nodeCount: Int
    val edgeCount: Int
    val computers: Array<MutableList<Int>>

    init {
        nodeCount = readln().toInt()
        edgeCount = readln().toInt()
        computers = Array(nodeCount + 1) { mutableListOf() }

        repeat(edgeCount) {
            readln().split(' ').map { it.toInt() }.also {
                computers[it[0]] += it[1]
                computers[it[1]] += it[0]
            }
        }
    }

    fun solution() {
        val queue = ArrayDeque<Int>()
        val visited = BooleanArray(nodeCount + 1) { false }
        var answer = 0

        queue.addLast(1)
        while (queue.isNotEmpty()) {
            val idx = queue.first()
            queue.removeFirst()
            visited[idx] = true
            for (i in computers[idx]) {
                if (visited[i])
                    continue
                queue.addLast(i)
                visited[i] = true
                answer += 1
            }
        }
        println(answer)
    }
}

fun main() {
    Boj2606().solution()
}