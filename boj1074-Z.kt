import kotlin.math.pow

class Boj1074 {
    val num: Int
    val row: Int
    val col: Int

    init {
        val input = readln().split(' ').map { it.toInt() }
        num = input[0]
        row = input[1]
        col = input[2]
    }
    fun z(r: Int, c: Int, n: Int): Int {
        if (n == 0)
            return 0
        val half = 2.0.pow(n - 1).toInt()
        if (r < half && c < half)
            return z(r, c, n - 1)
        else if (r < half)
            return half * half + z(r, c - half, n - 1)
        else if (c < half)
            return 2 * half * half + z(r - half, c, n - 1)
        return 3 * half * half + z (r - half, c - half, n - 1)
    }

    fun solution() {
        z(row, col, num).also { println(it) }
    }
}

fun main() {
    Boj1074().solution()
}