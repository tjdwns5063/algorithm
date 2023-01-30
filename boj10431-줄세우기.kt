class Boj10431 {
    val input = readln().split(' ').map {it.toInt()}

    fun solution() {
        val li = mutableListOf<Int>()
        var ans = 0
        li += input[1]
        for (i in 2..20) {
            if (li[li.lastIndex] > input[i]) {
                for (j in li.indices) {
                    if (li[j] > input[i]) {
                        ans += li.size - j
                        li.add(j, input[i])
                        break
                    }
                }
            } else {
                li += input[i]
            }
        }
        // println(li)
        println("${input[0]} $ans")
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj10431().solution()
    }
}