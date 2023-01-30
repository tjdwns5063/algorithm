class Boj1182 {
    val len: Int
    val target: Int
    val sequence: IntArray
    val lst = mutableListOf<Int>()
    var ans = 0

    init {
        readln()
            .split(' ')
            .map { it.toInt() }
            .also { len=it[0]; target=it[1] }

        sequence = readln()
            .split(' ')
            .map { it.toInt() }
            .toIntArray()
    }

    fun dfs(total: Int, visit: BooleanArray) {
        if (total == target)
            ++ans
        if (lst.size == len) {
            return
        }
        for (i in sequence.indices) {
            if (visit[i])
                continue
            visit[i] = true
            lst += sequence[i]
            dfs(lst.sum(), visit)
            lst.removeLast()
            for (j in i + 1 until len)
                visit[j] = false
        }
    }

    fun solution() {
        dfs(-100000000, BooleanArray(len) { false })
        println(ans)
    }
}

fun main() {
    Boj1182().solution()
}