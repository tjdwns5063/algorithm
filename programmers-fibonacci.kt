import java.math.BigInteger

class Fibonacci {
    fun solution(n: Int): Int {
        val fibo = arrayListOf(BigInteger("0"), BigInteger("1"))

        for (i in 2..n) {
            fibo += fibo[i - 2] + fibo[i - 1]
        }
        println(fibo)
        val answer = fibo[n].mod(BigInteger("1234567"))
        println(answer.toInt())
        return answer.toInt()
    }
}

fun main() {
    Fibonacci().solution(100)
}