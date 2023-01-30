import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Boj11660 {
    val n: Int
    val m: Int
    val numbers: Array<IntArray>
    val sumArr: Array<IntArray>
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    init {
        br.readLine().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        numbers = Array(n) { br.readLine().split(' ').map { it.toInt() }.toIntArray() }
        sumArr = Array(n + 1) { IntArray(n + 1) { 0 } }

        for (i in numbers.indices) {
            var sum = 0
            for (j in numbers.indices) {
                sum += numbers[i][j]
                sumArr[i + 1][j + 1] = sum
            }
        }
    }

    fun solution() {
        repeat(m) {
            val (start, end) = br.readLine().split(' ').map { it.toInt() }.let {
                listOf(Pair(it[0], it[1]), Pair(it[2], it[3]))
            }
//            println("start: $start end: $end")
            var sum = 0
            for (i in start.first..end.first) {
                sum += sumArr[i][end.second] - sumArr[i][start.second - 1]
            }
            bw.write("$sum\n")
        }
        bw.flush()
        bw.close()
    }
}

fun main() {
    Boj11660().solution()
}