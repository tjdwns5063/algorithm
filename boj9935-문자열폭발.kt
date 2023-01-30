import java.util.*
import kotlin.math.*

class Boj9935 {
    val s: String
    val t: String
    val stk: Stack<Char>
    val tempString = StringBuilder()

    init {
        s = readln()
        t = readln()
        stk = Stack<Char>()

        for (i in s.lastIndex downTo 0) {
            stk += s[i]
        }
    }

    // s * t
    fun solution() {
        println(stk)
        while (t[0] in s) {
            var currCnt = tempString.length
            while (stk.isNotEmpty()) {
                
            }
        }
    }
}

fun main() {
    Boj9935().solution()
}