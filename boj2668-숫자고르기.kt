import kotlin.math.*

class Boj2668 {
    val n: Int
    val nums: MutableList<Int>
    val visited: BooleanArray
    val setUp = mutableSetOf<Int>()
    val setDown = mutableSetOf<Int>()


    init {
        n = readln().toInt()

        nums = mutableListOf(0)
        visited = BooleanArray(n + 1) { false }
        repeat(n) {
            nums += readln().toInt()
        }

    }

    fun dfs(i: Int) {
        val num = nums[i]
        if (!visited[num]) {
            visited[num] = true
            setUp += i
            setDown += num
            dfs(num)
        }
    }

    fun solution() {
        val ansList = mutableListOf<Int>()

        for (i in 1..n) {
        
            dfs(i)
            if (setUp == setDown) {
                for (down in setDown) {
                    ansList += down
                }
            } else {
                for (down in setDown) {
                    visited[down] = false
                }
            }
            setUp.clear()
            setDown.clear()
        }
        println(ansList.size)
        ansList.sort()
        ansList.forEach { println(it) }
    }
}

fun main() {
    Boj2668().solution()
}