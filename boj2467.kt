import kotlin.math.*

class Boj2467 {
    val n: Int
    val fluids: List<Int>

    init {
        n = readln().toInt()
        fluids = readln().split(' ').map { it.toInt() }
        // println(fluids)
    }
    fun solution() {
        var start = 0
        var end = fluids.lastIndex
        var blend = fluids[start] + fluids[end]
        var minStart = start
        var minEnd = end

        while (start!=end) {
            val newBlend = fluids[start] + fluids[end]
            // println("blend: $blend newBlend: $newBlend")
            if (abs(blend) > abs(newBlend)) {
                minStart = start
                minEnd = end
                blend = newBlend
            }
            if (newBlend > 0) {
                --end
            } else if (newBlend < 0) {
                ++start
            } else {
                break
            }
        }
        println("${fluids[minStart]} ${fluids[minEnd]}")
    }
}

fun main() {
    Boj2467().solution()
}