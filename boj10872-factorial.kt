tailrec fun factorial(n: Int, ret: Int = 1): Int {
    if (n <= 0) return ret
    return factorial(n - 1, ret * n)
}

fun main() {
    println(factorial(readln().toInt()))
}