import java.util.*
import kotlin.math.*

class Boj9935 {
    val target: String
    val stk: Stack<Char>
    val stk2: Stack<Char>
    val ans = StringBuilder()

    init {
        stk = Stack<Char>()
        readln().forEach { stk += it }
        target = readln()
        stk2 = Stack<Char>()
    }

    // s * t
    fun solution() {
        while (stk.isNotEmpty()) {
            val s = stk.peek()
            
            if (s == target[0]) {
                var idx = 1
                repeat(target.length - 1) {
                    if (stk2.isNotEmpty() && stk2.peek() == target[idx]) {
                        stk += stk2.peek()
                        stk2.pop()
                        ++idx
                    }
                }
                if (idx != target.length) {
                    repeat(idx) {
                        stk2 += stk.peek()
                        stk.pop()
                    }
                } else {
                    repeat(idx) {
                        stk.pop()
                    }
                }
            } else {
                stk2 += s
                stk.pop()
            }
        }
        while (stk2.isNotEmpty()) {
            ans.append(stk2.peek())
            stk2.pop()
        }
        if (ans.isEmpty()) {
            println("FRULA")
            return
        }
        println(ans)
    }
}

fun main() {
    Boj9935().solution()
}