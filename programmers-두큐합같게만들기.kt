import java.util.Deque
import java.util.Queue

class CreatingSumEqualQueue {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0

        val arr1 = ArrayDeque<Long>()
        val arr2 = ArrayDeque<Long>()

        queue1.forEach {
            arr1 += it.toLong()
        }

        queue2.forEach {
            arr2 += it.toLong()
        }

        var total1 = arr1.sum()
        var total2 = arr2.sum()
        while (total1 != total2) {
            if (answer > (arr1.size + arr2.size) * 2)
                return -1
            if (total1 > total2) {
                val moved = arr1.first()
                arr1.removeFirst()
                arr2 += moved
                total1 -= moved
                total2 += moved
            } else {
                val moved = arr2.first()
                arr2.removeFirst()
                arr1 += moved
                total1 += moved
                total2 -= moved
            }
            ++answer
        }
        return answer
    }
}

fun main() {
    CreatingSumEqualQueue().solution(
        intArrayOf(1,1),
        intArrayOf(1,5)
    ).also { println(it) }
}