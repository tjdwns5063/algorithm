class Boj15652 {
    val n: Int
    val m: Int
    val lst = mutableListOf<Int>()
    val str = StringBuilder("")

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
    }

    fun backtracking(depth: Int, visit: BooleanArray) {
        if (depth == m) {
            lst.forEach { str.append(it); str.append(' ') }
            str.append("\n")
            return
        }

        for (i in 0 until n) {
            if (visit[i])
                continue
            lst += i + 1
            backtracking(depth + 1, visit)
            visit[i] = true
            lst.removeLast()
            for (j in i + 1 until n)
                visit[j] = false
        }
    }

    fun solution() {
        backtracking(0, BooleanArray(n) { false })
        print(str)
    }
}

fun main() {
    Boj15652().solution()
}