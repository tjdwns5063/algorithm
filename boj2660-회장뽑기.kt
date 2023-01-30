class Boj2660 {
    fun solution() {
        val nodeCnt = readln().toInt()
        val inf = 51 * 51
        val graph = Array(nodeCnt + 1) { IntArray(nodeCnt + 1) { inf } }

        while (true) {
            val (a, b) = readln().split(' ').map { it.toInt() }
            if (a == -1 && b == -1) {
                break
            } else {
                graph[a][b] = 1
                graph[b][a] = 1
            }
        }

        for (i in 1..nodeCnt) {
            graph[i][i] = 0
        }
        val table = Array(nodeCnt + 1) { graph[it] }
//        graph.forEach { println(it.toList()) }
//        println()
//        table.forEach { println(it.toList()) }
//        println()
        for (k in 1..nodeCnt) {
            for (i in 1..nodeCnt) {
                for (j in 1..nodeCnt) {
                    if (table[i][j] > table[i][k] + table[k][j]) {
                        table[i][j] = table[i][k] + table[k][j]
                    }
                }
            }
        }
//        table.forEach { println(it.toList()) }

        val answer = Array(nodeCnt + 1) { inf }
        for (i in 1..nodeCnt) {
            var max = 0
            for (j in 1..nodeCnt) {
                if (table[i][j] == 0)
                    continue
                if (max < table[i][j])
                    max = table[i][j]
            }
            answer[i] = max
        }
        val score = answer.minOf { it }
        val numb = arrayListOf<Int>()
        for (i in 1..nodeCnt) {
            if (answer[i] == score)
                numb += i
        }
//        println(answer.toList())
        println("$score ${numb.size}")
        numb.forEach {
            print("$it ")
        }
    }
}

fun main() {
    Boj2660().solution()
}