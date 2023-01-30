class Boj15655 {
    val n: Int
    val m: Int
    val lst = mutableListOf<Int>()
    val numbers: IntArray
    var str = StringBuilder("")

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
        numbers.sort()
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
            visit[i] = true
            lst += numbers[i]
            backtracking(depth + 1, visit)
            lst.removeLast()
            for (j in i + 1 until n) {
                visit[j] = false
            }
        }
    }

    fun solution() {
        backtracking(0, BooleanArray(n) {false})
        print(str)
    }
}

fun main() {
    Boj15655().solution()
}