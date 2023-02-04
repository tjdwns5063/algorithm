class Boj7490 {
    val n = readln().toInt()
    val numbers = CharArray(9) { (it+1 + '0'.code).toChar() }
    val symbols = charArrayOf(' ', '+', '-');
    val list = mutableListOf<Char>();

    fun dfs(depth: Int) {
        if (depth >= n-1) {
            val quiz = mutableListOf<Char>()
            quiz += '1'
            for (i in list.indices) {
                quiz += list[i]
                quiz += numbers[i+1]
            }

            val numList = mutableListOf<Int>()
            var num = 0
            for (i in quiz.indices) {
                if (quiz[i] != '+' && quiz[i] != '-' && quiz[i] != ' ') {
                    num += quiz[i].digitToInt()
                } else if (quiz[i] == ' ') {
                    num *= 10
                } else {
                    numList += num
                    num = 0
                }
            }
            numList += (num)
            var sum = numList[0]
            var idx = 0
            for (i in list.indices) {
                if (list[i] == ' ')
                    continue
                if (list[i] == '-') {
                    sum -= numList[idx+1]
                } else if (list[i] == '+') {
                    sum += numList[idx+1]
                }
                ++idx
            }
            if (sum == 0) {
                println(String(quiz.toCharArray()))
            }
            // println(numList)
            // println(sum)
            return
        }
        for (i in symbols.indices) {
            list += symbols[i]
            dfs(depth+1)
            list.removeLast()
        }
    }

    fun solution() {
        dfs(0)
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj7490().solution()
        println()
    }
}