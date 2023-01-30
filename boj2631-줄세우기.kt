import kotlin.math.*

class Boj2631 {
    val n = readln().toInt()
    val arr = IntArray(n + 1) { 0 }
    val ans = IntArray(n + 1) { 0 }


    init {
        repeat(n) {
            arr[it + 1] = readln().toInt()
        }
    }

    fun solution() {
        for (i in arr.indices) {
            for (j in arr.indices) {
                if (arr[i] > arr[j]) {
                    ans[i] = max(ans[j] + 1, ans[i])
                }
            }
        }
        println(n - ans.maxOf{it})
    }
}

fun main() {
    Boj2631().solution()
}