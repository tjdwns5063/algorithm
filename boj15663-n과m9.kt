class Boj15663 {
    val n: Int
    val m: Int
    val numbers: IntArray
    val lst = mutableListOf<Int>()
    val set = mutableSetOf<MutableList<Int>>()
    val ans = StringBuilder("")

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
            if (lst !in set) {
                lst.forEach { ans.append(it); ans.append(' ') }
                ans.append("\n")
            }
            set += lst
            return
        }

        for (i in 0 until n) {
            if (visit[i])
                continue
            lst += numbers[i]
            visit[i] = true
            backtracking(depth + 1, visit)
            lst.removeLast()
            visit[i] = false
        }
    }

    fun solution() {
        backtracking(0, BooleanArray(n) { false })
        print(ans)
    }
}

fun main() {
    Boj15663().solution()
}