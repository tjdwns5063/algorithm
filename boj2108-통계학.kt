// time: 2sec
// memory: 256MB
// n: 1 <= n <= 500_000
// n은 홀수

import kotlin.math.*

class Boj2108 {
    val n: Int
    val list: MutableList<Int>
    val cntList: IntArray

    init {
        n = readln().toInt()
        list = mutableListOf()
        cntList = IntArray(8002) { 0 }
        repeat(n) {
            list += readln().toInt()
        }
        list.sort()
    }

    fun solution() {
        var average: Double = 0.0
        var center: Int = list[n/2]
        var range: Int = list[list.lastIndex] - list[0]

        list.forEach { average += it.toDouble() }
        println(round(average / n.toDouble()).toInt())
        println(center)


        list.forEach { value -> 
            cntList[value + 4000] += 1
        }
        var mx = cntList.maxOf { it }
        var mxCnt = cntList.count { it == mx }
        var check = 0
        if (mxCnt > 1) {
            for (i in cntList.indices) {
                if (check >= 1 && cntList[i] == mx) {
                    println(i - 4000)
                    break
                }
                if (check < 1 && cntList[i] == mx) {
                    ++check
                }
            }
        } else {
            println(cntList.indexOf(mx)-4000)
        }
        
        println(range)
    }
}

fun main() {
    Boj2108().solution()
}