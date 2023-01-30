class Boj1253 {
    val size: Int
    val numbers: IntArray
    val checker: MutableMap<Int, Boolean>

    init {
        size = readln().toInt()
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
        numbers.sort()
        checker = numbers.toSet().associateWith { false }.toMutableMap()
    }

    fun solution() {
        numbers.forEachIndexed { i, v ->
            twoPointer(i, v)
        }
        var ans = 0
        numbers.forEach {
            if (checker[it] == true)
                ++ans
        }.also { println(ans) }
    }

    private fun twoPointer(idx: Int, target: Int) {
        var start = 0
        var end = numbers.lastIndex

        while (start != end) {
            if (start == idx) {
                start += 1
                continue
            }
            if (end == idx) {
                end -= 1
                continue
            }
            if (numbers[start] + numbers[end] > target) {
                end -= 1
            } else if (numbers[start] + numbers[end] < target) {
                start += 1
            } else {
                checker[target] = true
                break
            }
        }
    }
}

fun main() {
    Boj1253().solution()
}