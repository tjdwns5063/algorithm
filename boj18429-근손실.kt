class Boj18429 {
    val n: Int
    val k: Int
    val kits: IntArray
    var ans = 0
//    val list = arrayListOf<Int>()

    init {
        val input = readln().split(' ').map { it.toInt() }
        n = input[0]
        k = input[1]
        kits = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun dfs(depth: Int, visit: BooleanArray, weight: Int) {
        if (weight < 500)
            return
        if (depth == n) {
            ans += 1
//            println("$list")
            return
        }
        for (i in kits.indices) {
            if (visit[i])
                continue
//            list += i
            visit[i] = true
            dfs(depth + 1, visit, weight + kits[i] - k)
//            list.removeLast()
            visit[i] = false
        }
    }

    fun solution() {
        dfs(0,  BooleanArray(n) { false }, 500)
        println(ans)
    }
}

fun main() {
    Boj18429().solution()
}