import kotlin.math.*

class Boj18870 {
    val n: Int
    val numbers: IntArray
    val sortedDistinctArray: List<Int>

    init {
        n = readln().toInt()
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
        sortedDistinctArray = numbers.sorted().distinct()
    }

    fun solution() {
        val ans = StringBuilder()
        // println(sortedDistinctArray)

        for (number in numbers) {
            ans.append("${lower_bound(number)} ")
        }
        println(ans)
    }

    // fun upper_bound(target: Int):Int {
    //     var start = 0
    //     var end = sortedDistinctArray.size

    //     while (start < end) {
    //         var mid = (start + end) / 2

    //         if (sortedDistinctArray[mid] <= target) {
    //             start = mid + 1
    //         } else {
    //             end = mid
    //         }
    //     }
    //     return end
    // }

    fun lower_bound(target: Int):Int {
        var start = 0
        var end = sortedDistinctArray.size

        while (start < end) {
            var mid = (start + end) / 2

            if (sortedDistinctArray[mid] >= target) {
                end = mid
            } else {
                start = mid + 1
            }
        }
        return start
    }
}

fun main() {
    Boj18870().solution()
}