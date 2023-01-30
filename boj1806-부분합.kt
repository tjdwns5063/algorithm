import kotlin.math.min

class Boj1806 {
    val n: Int
    val sum: Int
    val numbers: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            sum = it[1]
        }
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    fun solution() {
        var start = 0
        var end = 0
        var currSum = 0
        var currLen = 0
        var length = numbers.size

//        println(numbers.contentToString())

        if (numbers.sum() < sum) {
            println(0)
            return
        }
        var iter = 0

        while (true) {
            if (currSum >= sum && start < numbers.size) {
                length = min(length, currLen)
                currSum -= numbers[start]
                start += 1
                currLen -= 1
                iter = 0
            } else if (currSum < sum && end < numbers.size) {
                currSum += numbers[end]
                currLen += 1
                end += 1
                iter = 0
            }
//            println("start: $start end: $end")
            ++iter
            if (start >= numbers.size && end >= numbers.size )
                break
            if (iter > 1)
                break
        }
        println(length)
    }
}

fun main() {
    Boj1806().solution()
}