// time = 2sec
// memory = 128mb
// n = 1 <= n <= 50000

import java.util.*
import kotlin.math.*

class Boj1863 {
    val n: Int
    val stk: Stack<Int>
    val arr: IntArray

    init {
        n = readln().toInt()
        stk = Stack<Int>()
        arr = IntArray(500001) { 0 }
        repeat(n) { i ->
            readln().split(' ').map{it.toInt()}.also{
                arr[i] = it[1]
            }
        }
    }

    fun solution() {
        var ans = 0 
        for (i in 0..n) {
            val y = arr[i]
            while (stk.isNotEmpty() && stk.peek() > y) {
                ++ans
                stk.pop()
            }
            if (stk.isNotEmpty() && stk.peek() == y)
                continue
            stk += y
        }
        println(ans)
    }
}

fun main() {
    Boj1863().solution()
}