class Boj7568 {
    val n: Int
    val arr: Array<Pair<Int, Int>>

    init {
        n = readln().toInt()
        arr = Array(n) { readln().split(' ').map { it.toInt() }.let { it[0] to it[1] } }
    }

    fun solution() {
        var ans = StringBuilder()

        for (i in 0 until n) {
            var cnt = 0
            for (j in 0 until n) {
                if (i == j)
                    continue
                if (arr[i].first < arr[j].first && arr[i].second < arr[j].second) {
                    ++cnt
                }
            }
            ans.append("${cnt + 1} ")
        }
        println(ans)
    }
}

fun main() {
    Boj7568().solution()
}