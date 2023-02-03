import kotlin.math.*

class Boj9527 {
    val n: Long
    val m: Long
    val arr = LongArray(56) { 0L }

    init {
        readln().split(' ').map {it.toLong()}.also {
            n = it[0]
            m = it[1]
        }

        arr[0] = 1L
        var two = 2L
        for (i in 1..arr.lastIndex) {
            arr[i] = 2 * arr[i - 1] + two
            two *= 2L
        }
    }

    fun solution() {
        println(arr.contentToString())
        println(getAns(m) - getAns(n - 1))
    }

    fun getAns(num: Long): Long {
        var target: Long = num
        var ans = (num and 1) // 마지막 비트가 0인지 1인지 생각해서 미리 더해둠.
        var n = 1L
        for (i in 55 downTo 1) {
            if (((n shl i) and target) > 0) { // 2^i의 맨 앞 비트와 target의 맨 앞 비트 비교하여 일치하면
                ans += arr[i - 1] + (target - (n shl i) + 1) 
                //arr[i-1] = 맨 첫번째 비트를 제외한곳에서 1이 등장하는 횟수
                //(target - (n shl i) + 1) = 맨 첫번째 자리에서 1이 등장하는 횟수
                target = (target - (n shl i))
            }
        }
        return ans
    }
}

fun main() {
    Boj9527().solution()
}