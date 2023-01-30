import kotlin.math.*

class Boj22251 {
    val n: Int
    val k: Int
    val p: Int
    val x: Int
    val numbers: List<List<Boolean>>
    
    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            k = it[1]
            p = it[2]
            x = it[3]
        }

        numbers = listOf(
            listOf(true, true, true, false, true, true, true), // 0
            listOf(false, false, true, false, false, true, false), // 1
            listOf(true, false, true, true, true, false, true), // 2
            listOf(true, false, true, true, false, true, true), // 3
            listOf(false, true, true, true, false, true, false), // 4
            listOf(true, true, false, true, false, true, true), // 5
            listOf(true, true, false, true, true, true, true), // 6
            listOf(true, false, true, false, false, true, false), // 7
            listOf(true, true, true, true, true, true, true), // 8
            listOf(true, true, true, true, false, true, true) // 9
        )
    }

    fun solution() {
        val convertArray = Array(10) { IntArray(10) { 0 } }
        val currNumber = IntArray(k) { 0 }

        var idx = currNumber.lastIndex

        for (c in x.toString().reversed()) {
            currNumber[idx] = c.digitToInt()
            --idx
        }
        for (i in 0 until 10) {
            val curr = numbers[i]
            for (j in 0 until 10) {
                if (i == j)
                    continue
                val comp = numbers[j]
                var cnt = 0
                for (k in curr.indices) {
                    if (curr[k] != comp[k])
                        ++cnt
                }
                convertArray[i][j] = cnt
            }
        }

        var curr = n
        val tempArr = IntArray(k) { 0 }
        val sb = StringBuilder()
        var ans = 0

        // for (i in 0..9) {
            // for (j in 0..9) {
                // print("${convertArray[i][j]} ")
            // }
            // println()
        // }
        while (curr > 0) {
            var idx = tempArr.lastIndex
            sb.append(curr)
            for (i in sb.lastIndex downTo 0) {
                tempArr[idx] = sb[i].digitToInt()
                --idx
            }
            var sum = 0
            // println(currNumber.contentToString())
            // println(tempArr.contentToString())
            for (i in 0 until k) {
               sum += convertArray[currNumber[i]][tempArr[i]]
            }
            // println("sum: $sum curr: $curr")
            if (sum <= p)
                ans += 1
            // println(sum)
            sb.clear()
            for (i in tempArr.indices) {
                tempArr[i] = 0
            }
            curr -= 1
        }
        println(ans - 1)
    }
}

fun main() {
    Boj22251().solution()
}