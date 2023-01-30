import kotlin.math.*

class MakePrimeNumber {
    var answer = 0
    var number = mutableListOf<Int>()

    fun isPrime(num: Int): Boolean {
        if (num == 2) return true
        if (num < 2 || num % 2 == 0) return false

        var div = 2
        val root = sqrt(num.toDouble()).toInt()
        while (div <= root) {
            if (num % div == 0)
                return false
            ++div
        }
        return true
    }

    fun backtracking(nums: IntArray, visited: BooleanArray) {
        if (number.size == 3) {
            val sum = number.fold(0) { acc, i ->
                acc + i
            }
            if (isPrime(sum)) {
                println("prime: $number")
                ++answer
            }
            return
        }
        for (i in nums.indices) {
            if (!visited[i]) {
                number.add(nums[i])
                visited[i] = true
                backtracking(nums, visited)
                number.removeAt(number.lastIndex)
                for (j in i + 1 until nums.size) {
                    visited[j] = false
                }
            }
        }
    }

    fun solution(nums: IntArray): Int {
        backtracking(nums, BooleanArray(nums.size) { false })
        return answer
    }
}

fun main() {
    MakePrimeNumber().solution(intArrayOf(1,2,3,4)).also { println(it) }
}