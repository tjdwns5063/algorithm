class Boj9655 {
    var n: Int = readln().toInt()

    fun solution() {
        var cnt = 0
        while (n > 3) {
            n -= 3
            ++cnt
        }
        println(if ((cnt + n) % 2 == 1) "SK" else "CY")
    }
}

fun main() {
    Boj9655().solution()
}