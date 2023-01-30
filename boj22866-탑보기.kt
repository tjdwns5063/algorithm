import kotlin.math.*
import java.util.*
import java.io.*


class Boj22866 {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val towers = br.readLine().split(' ').map { it.toInt() }.toIntArray()
    val stk = Stack<Pair<Int, Int>>()
    val ansLeft: IntArray
    val ansRight: IntArray
    val ansCnt: IntArray

    init {
        ansLeft = IntArray(n) { -1 }
        ansRight = IntArray(n) { -1 }
        ansCnt = IntArray(n) { 0 }
    }

    fun solution() {
        stk.push(0 to towers[0])
        for (i in 1..towers.lastIndex) {
            while (stk.isNotEmpty() && stk.peek().second <= towers[i]) {
                stk.pop()
            }
            if (stk.isNotEmpty()) {
                ansLeft[i] = stk.peek().first
                ansCnt[i] += stk.size
            }
            stk.push(i to towers[i])
        }
        stk.clear()
        stk.push(towers.lastIndex to towers[towers.lastIndex])
        for (i in towers.lastIndex - 1 downTo 0) {
            while (stk.isNotEmpty() && stk.peek().second <= towers[i]) {
                stk.pop()
            }
            if (stk.isNotEmpty()) {
                ansRight[i] = stk.peek().first
                ansCnt[i] += stk.size
            }
            stk.push(i to towers[i])
        }

        for (i in 0 until n) {
            if (ansCnt[i] != 0) {
                val leftDist = if (ansLeft[i] == -1) n + 1 else abs(ansLeft[i] - i) 
                val rightDist = if (ansRight[i] == -1) n + 1 else abs(ansRight[i] - i)

                val id = if (leftDist > rightDist) ansRight[i] else ansLeft[i]
                bw.write("${ansCnt[i]} ${id + 1}\n")
            }
            else
                bw.write("${ansCnt[i]}\n")
        }

        bw.flush()
        bw.close()
    }
}

fun main() {
    Boj22866().solution()

}