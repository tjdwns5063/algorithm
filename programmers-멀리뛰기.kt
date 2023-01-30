import java.math.BigInteger

class LongJump {
    fun comb(n: Int, r: Int): BigInteger {
        var parent = BigInteger("1")
        var child = BigInteger("1")

        println("n: $n r: $r")
        repeat(r) {
            val num = (n - it).toBigInteger()
            parent *= num
        }

        repeat(r) {
            val num = (r - it).toBigInteger()
            child *= num
        }
        return parent / child
    }

    fun solution(n: Int): Long {
        var answer = BigInteger("1")

        val cnt = n / 2
        var nn = n - 1
        var r = 1
        repeat(cnt) {
            answer += comb(nn, r)
            r += 1
            nn -= 1
        }
        return answer.mod(BigInteger("1234567")).toLong()
    }
}

fun main() {
    LongJump().solution(100).also { println(it) }
}