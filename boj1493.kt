class Boj2493 {
    val n: Int
    val towers: IntArray
    
    init {
        n = readln().toInt()
        towers = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun solution() {
        val stk = ArrayDeque<Pair<Int, Int>>()
        val ans = IntArray(n) { 0 }

        for (i in towers.size - 1 downTo 0) {
            while (stk.isNotEmpty()) {
                val (height, idx) = stk.last()
                if (height <= towers[i]) {
                    ans[idx] = i + 1
                    stk.removeLast()
                } else {
                    break
                }
            }
            stk += towers[i] to i
        }
        val ansString = StringBuilder()
        ans.forEach { 
            ansString.append(it)
            ansString.append(" ")
        }
        println(ansString)
    }
}

fun main() {
    Boj2493().solution()
}