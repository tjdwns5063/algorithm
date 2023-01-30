class Boj11659 {
    val n: Int
    val m: Int
    val numbers: IntArray
    val sumArr: IntArray

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
        var sum = 0
        sumArr = IntArray(n + 1) { 0 }
        for (i in numbers.indices) {
            sum += numbers[i]
            sumArr[i + 1] = sum
        }
//        println(sumArr.contentToString())
    }

    fun solution() {
        repeat(m) {
            val (start, end) = readln().split(' ').map { it.toInt() }

            println(sumArr[end] - sumArr[start - 1])
        }
    }
}

fun main() {
    Boj11659().solution()
}