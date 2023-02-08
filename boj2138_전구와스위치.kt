import kotlin.math.*

class Boj2138 {
    val n: Int = readln().toInt()
    val before: IntArray
    val after: IntArray

    init {
        before = readln().toCharArray().map{it.digitToInt()}.toIntArray()
        after = readln().toCharArray().map{it.digitToInt()}.toIntArray()
    }

    fun isEnd(origin:IntArray, comp:IntArray): Boolean {
        for (i in 0 until n) {
            if (origin[i] != comp[i]) {
                return false
            }
        }
        return true
    }

    fun pressSwitch(idx: Int, bulbs: IntArray) {
        when (idx) {
            0 -> {
                bulbs[0] = if (bulbs[0] == 0) 1 else 0
                bulbs[1] = if (bulbs[1] == 0) 1 else 0
            }
            n-1 -> {
                bulbs[n-1] = if (bulbs[n-1] == 0) 1 else 0
                bulbs[n-2] = if (bulbs[n-2] == 0) 1 else 0
            }
            else -> {
                bulbs[idx-1]= if (bulbs[idx-1] == 0) 1 else 0
                bulbs[idx] = if (bulbs[idx] == 0) 1 else 0
                bulbs[idx+1] = if (bulbs[idx+1] == 0) 1 else 0
            }
        }
    } 

    fun solution() {
        val cnt0 = getCnt(0)
        val cnt1 = getCnt(1)

        if (cnt0 == -1 && cnt1 == -1) {
            println(-1)
            return
        }
        if (cnt0 == -1 && cnt1 != -1) {
            println(cnt1)
            return
        } 
        if (cnt0 != -1 && cnt1 == -1) {
            println(cnt0)
            return
        } 
        println(min(cnt0, cnt1))
    }

    fun getCnt(flag: Int): Int {
        val tempBulb = IntArray(n) { 0 }
        var cnt = 0

        for (i in 0 until n) {
            tempBulb[i] = before[i]
        }

        if (flag == 1) {
            cnt = 1
            pressSwitch(0, tempBulb)
        }
        
        // println(tempBulb.contentToString())
        if (isEnd(after, tempBulb)) {
            return cnt
        }
        for (i in 1 until n) {
            if (tempBulb[i-1] != after[i-1]) {
                ++cnt
                pressSwitch(i, tempBulb)   
            }
        }
        if (isEnd(after, tempBulb)) {
            return cnt
        }
        return -1
    }
}

fun main() {
    Boj2138().solution()
}