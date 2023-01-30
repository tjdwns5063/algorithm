class Boj2512 {
    val n: Int
    val budgets: IntArray
    val limit: Int

    init {
        n = readln().toInt()
        budgets = readln().split(' ').map { it.toInt() }.toIntArray()
        limit = readln().toInt()
        budgets.sort()
    }

    fun parametricSearch() {
        var start = 1
        var end = 1_000_000_000
        var ans = 0

        while (start <= end) {
            var mid = (start + end) / 2

            if (checkPossible(mid)) {
                ans = mid
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        println(ans)
    }

    fun checkPossible(target: Int): Boolean {
        var curr = 0

        for (i in budgets.indices) {
            if (budgets[i] > target)
                curr += target
            else
                curr += budgets[i]
        }
        return curr <= limit
    }

    fun solution() {
        if (budgets.sum() <= limit) {
            println(budgets[budgets.lastIndex])
            return
        }
        parametricSearch()
    }
}

fun main() {
    Boj2512().solution()
}