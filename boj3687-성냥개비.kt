import kotlin.math.*
import kotlin.text.*

class Boj3687 {
    val n: Int
    val minArr = Array<StringBuilder>(102) { StringBuilder() }
    val maxArr = Array<StringBuilder>(102) { StringBuilder() }

    init {
        n = readln().toInt()
    }

    fun solution() {
        fillMaxArr()
        fillMinArr()
        println("${minArr[n]} ${maxArr[n]}")
    }

    fun fillMinArr() {
        minArr[2].append(1)
        minArr[3].append(7)
        minArr[4].append(4)
        minArr[5].append(2)
        minArr[6].append(6)
        minArr[7].append(8)

        for (curr in 8..100) {
            var start = curr / 2
            var end = if (curr % 2 == 1) start + 1 else start
            while (minArr[start].isNotEmpty() && minArr[end].isNotEmpty()) {
                 
                val startPlusEnd = StringBuilder(minArr[start]).append(if (minArr[end].toString() == "6") "0" else minArr[end])
                val endPlusStart = StringBuilder(minArr[end]).append(if (minArr[start].toString() == "6") "0" else minArr[start])

                // 왼쪽이 작으면 음수반환
                if (compMin(startPlusEnd, endPlusStart) < 0) {
                    if (minArr[curr].isNotEmpty() && compMin(startPlusEnd, minArr[curr]) < 0) {
                        minArr[curr] = startPlusEnd
                    } else if (minArr[curr].isEmpty()) {
                        minArr[curr] = startPlusEnd
                    }
                } else {
                    if (minArr[curr].isNotEmpty() && compMin(endPlusStart, minArr[curr]) < 0) {
                        minArr[curr] = endPlusStart
                    } else if (minArr[curr].isEmpty()) {
                        minArr[curr] = endPlusStart
                    }
                }
                --start
                ++end
            }
        }
    }

    fun compMin(s1: StringBuilder, s2: StringBuilder): Int {
        var idx = 0
        var left = 0
        var right = 0

        if (s1.length != s2.length) {
            return s1.length - s2.length
        }
        while (idx < s1.length) {
            left = s1[idx].digitToInt()
            right = s2[idx].digitToInt()

            if (left != right)
                break
            ++idx
        }
        if (idx == s1.length || idx == s2.length)
            return 0
        return left - right
    }

    fun fillMaxArr() {
        maxArr[2].append(1)
        maxArr[3].append(7)

        for (curr in 4..100) {
            var start = curr / 2
            var end = if (curr % 2 == 1) start + 1 else start
            while (maxArr[start].isNotEmpty() && maxArr[end].isNotEmpty()) {
                val startPlusEnd = StringBuilder(maxArr[start]).append(maxArr[end])
                val endPlusStart = StringBuilder(maxArr[end]).append(maxArr[start])

                if (startPlusEnd.toString().compareTo(endPlusStart.toString()) > 0) {
                    maxArr[curr] = startPlusEnd
                } else {
                    maxArr[curr] = endPlusStart
                }
                --start
                ++end
            }
        }
    }
}

fun main() {
    val e = ArrayDeque<Int>()
    val t = System.currentTimeMillis()
    for (i in 1..100000) {
        e += i
    }
    val at = System.currentTimeMillis()
    println(at - t)
}