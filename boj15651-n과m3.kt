import java.io.BufferedWriter
import java.io.OutputStreamWriter

class Boj15651 {
    val n: Int
    val m: Int
    val lst = mutableListOf<Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
    }

    fun backtracking(depth: Int) {
        if (depth == m) {
            val str = StringBuilder("")
            lst.forEach { str.append(it); str.append(' ') }
            str.append("\n")
            bw.write(str.toString())
            return
        }

        for (i in 1..n) {
            lst += i
            backtracking(depth + 1)
            lst.removeLast()
        }
    }

    fun solution() {
        backtracking(0)
        bw.flush()
        bw.close()
    }
}

fun main() {
    Boj15651().solution()
}