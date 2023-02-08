import kotlin.math.*

//time=1sec
//memory=512mb
//n= 1 <= n <= 100_000


class Boj24337 {
    val n: Int
    val a: Int
    val b: Int
    val buildings: ArrayDeque<Int>
    val ans: MutableList<Int>

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            a = it[1]
            b = it[2]
        }
        buildings = ArrayDeque()
        ans = mutableListOf()
    }

    fun solution() {
        // var curr = 0
        // var cnt = n

        if (a + b > n + 1) {
            println(-1)
            return
        }
        var highest = max(a, b)
        if (a>b) {
            for (i in 1..highest) {
                buildings += i
            }
            if (b == 1) {
                repeat(n-a) {
                    buildings.addFirst(1)
                }
            } else {
                var curr = b-1
                while (buildings.size < n && curr > 0) {
                    buildings += curr
                    --curr
                }
                while (buildings.size < n) {
                    buildings.addFirst(1)
                }
            }
            buildings.forEach {
                print("$it ")
            }
        } else {
            for (i in highest downTo 1) {
                buildings += i
            }
            if (a == 1) {
                repeat(n-b) {
                    buildings.add(1, 1)
                }
            } else {
                var curr = a-1
                while (buildings.size < n && curr > 0) {
                    buildings.addFirst(curr)
                    --curr
                }
                while (buildings.size < n) {
                    buildings.addFirst(1)
                }
            }
            buildings.forEach {
                print("$it ")
            }
        }
    }
}

fun main() {
    Boj24337().solution()
}