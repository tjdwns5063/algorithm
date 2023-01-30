class Boj15650 {
    val n: Int
    val m: Int
    val lst = mutableListOf<Int>()

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
    }

    fun backtracking(depth: Int, visit: BooleanArray) {
        if (depth == m) {
            lst.forEach { print("$it ") }
            println()
            return
        }

        for (i in 0 until n) {
            if (visit[i])
                continue
            visit[i] = true
            lst += i + 1
            backtracking(depth + 1, visit)
            lst.removeLast()
            for (j in i + 1 until n) {
                visit[j] = false
            }
        }
    }

    fun solution() {
        backtracking(0, BooleanArray(n) { false })
    }
}

fun main() {
    Boj15650().solution()
}