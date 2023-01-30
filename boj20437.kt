import kotlin.math.*

class Boj20437 {
    val str: String
    val k: Int
    val strMap: MutableMap<Char, Int>

    init {
        str = readln()
        k = readln().toInt()
        strMap = mutableMapOf<Char, Int>()

        for (c in 'a'..'z') {
            strMap[c] = 0
        }

        for (c in str) {
            strMap[c] = strMap.getValue(c) + 1
        }
    }

    fun solution() {
        var longest = -1
        var shortest = Int.MAX_VALUE
        
        for (i in 0 until str.length) {
            if (strMap.getValue(str[i]) < k)
                continue

            var cnt = 0
            for (j in i until str.length) {
                if (str[i] == str[j])
                    ++cnt
                if (cnt == k) {
                    shortest = min(shortest, j - i + 1)
                    longest = max(longest, j - i  + 1)
                    break
                }
            }
        }
        if (longest == Int.MAX_VALUE || longest == -1) {
            println(-1)
            return
        }
        println("$shortest $longest")
    }

    // fun getShortest(): Int {
    //     val strMap2 = mutableMapOf<Char, Int>()

    //     for (c in 'a'..'z') {
    //         strMap2 += c to 0
    //     }

    //     var shortest = Int.MAX_VALUE
    //     var end = 0

    //     for (start in 0 until str.length) {
    //         while (end < str.length && !strMap2.containsValue(k)) {
    //             strMap2[str[end]] = strMap2.getValue(str[end]) + 1
    //             ++end
    //         }
    //         // println("start: $start end: $end")
    //         if (strMap2.containsValue(k))
    //             shortest = min(shortest, end - start)
    //         strMap2[str[start]] = strMap2.getValue(str[start]) - 1
    //     }
    //     return shortest
    // }

    // 모든 범위 검검사  못못하하고고있있음  수수정정필필요요

    // fun getLongest(): Int {
    //     var longest = 0
    //     val copy = HashMap(strMap)
    //     val mapList = mutableListOf<MutableMap<Char, Int>>()
    //     mapList += HashMap(strMap)

    //     println(strMap)
    //     for (start in 0 until str.length) {
    //         val currMap = mapList[start]
    //         var end = str.lastIndex
    //         while (end != start && str[start] != str[end]) {
    //             currMap[str[end]] = currMap.getValue(str[end]) - 1
    //             --end
    //         }
    //         println(str.substring(start, end + 1))
    //         println(currMap[str])
    //         if (str[start] == str[end] && currMap[str[start]] == k) {
    //             longest = max(longest, end - start)
    //         }
    //         copy[str[start]] = copy.getValue(str[start]) - 1
    //         mapList += HashMap(copy)
    //     }
    //     return longest + 1
    // }
}

fun main() {
    repeat(readln().toInt()) {
       Boj20437().solution()
    }
}