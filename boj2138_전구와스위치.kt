class Boj2138 {
    val n: Int = readln().toInt()
    val before: IntArray
    val after: IntArray
    val prev: IntArray
    var ans = 0
    var matchFlag = false
    var endflag = false

    init {
        before = readln().toCharArray().map{it.digitToInt()}.toIntArray()
        after = readln().toCharArray().map{it.digitToInt()}.toIntArray()
        prev = IntArray(n) { 0 }
    }

    fun isEnd(): Boolean {
        var cnt = 0

        for (i in 0 until n) {
            if (after[i] != before[i]) {
                break
            }
            ++cnt
        }
        return if (cnt == n) true else false
    }

    fun solution() {
        // println(before.contentToString())
        // println(after.contentToString())
        restore(0)
        if (!matchFlag)
            println(-1)
    }
}

fun main() {
    Boj2138().solution()
}