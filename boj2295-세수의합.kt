import kotlin.math.*

class Boj2295 {
    val n: Int
    val numbers: IntArray
    val subSumList: MutableList<Int>
    var ans = -1
    
    init {
        n = readln().toInt()
        numbers = IntArray(n) { readln().toInt() }
        subSumList = mutableListOf()
    }

    fun binarySearch(target: Int): Boolean {
        var start = 0
        var end = subSumList.size - 1

        while (start <= end) {
            var mid = (start + end) / 2

            if (subSumList[mid] > target) {
                end = mid - 1
            } else if (subSumList[mid] < target) {
                start = mid + 1
            } else {
                return true
            }
        }
        return false
    } 

    fun solution() {
        for (i in numbers.indices) {
            for (j in i until numbers.size) {
                subSumList += numbers[i] + numbers[j]
            }
        }
        numbers.sort()
        subSumList.sort()
        for (i in numbers.lastIndex downTo 0) {
            for (j in 0 until i) {
                if (binarySearch(numbers[i] - numbers[j])) {
                    ans = max(numbers[i], ans)
                }
            }
        }
        println(ans)
    }
}

fun main() {
    Boj2295().solution()
}