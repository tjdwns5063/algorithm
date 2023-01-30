class Boj1920 {
    val n: Int
    val m: Int
    val numbers: IntArray
    val found: IntArray
    val ans = StringBuilder("")

    init {
        n = readln().toInt()
        numbers = readln().split(' ').map { it.toInt() }.toIntArray()
        m = readln().toInt()
        found = readln().split(' ').map { it.toInt() }.toIntArray()

        numbers.sort()
    }

    fun recursiveBinarySearch(target: Int, start: Int, end: Int) {
        if (start > end) {
            ans.append("0\n")
            return
        }
        val mid = (start + end) / 2

        if (numbers[mid] > target) {
            recursiveBinarySearch(target, start, mid - 1)
        } else if (numbers[mid] < target) {
            recursiveBinarySearch(target, mid + 1, end)
        } else {
            ans.append("1\n")
            return
        }
    }
    fun iterateBinarySearch(target: Int, start: Int, end: Int) {
        var st = start
        var en = end

        while (st <= en) {
            val mid = (st + en) / 2

            if (numbers[mid] == target)
                ans.append("1\n")
            else if (numbers[mid] > target)
                en = mid - 1
            else
                st = mid + 1
        }
        ans.append("0\n")
    }

    fun solution() {
        found.forEach { iterateBinarySearch(it, 0, numbers.lastIndex) }
        print(ans)
    }
}

fun main() {
    Boj1920().solution()
}