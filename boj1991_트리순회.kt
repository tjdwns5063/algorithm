class Boj1991 {
    val n: Int
    val leftArr: CharArray
    val rightArr: CharArray

    init {
        n = readln().toInt()
        leftArr = CharArray(27) { '.' }
        rightArr = CharArray(27) { '.' }

        repeat(n) { idx ->
            readln().split(' ').also {
                // println(it)
                leftArr[it[0][0].code - 'A'.code + 1] = it[1][0]
                rightArr[it[0][0].code - 'A'.code + 1] = it[2][0]
            }
        }
    }

    fun preOrder(curr: Char) {
        print(curr)
        val next = (curr.code - 'A'.code + 1)
        if (leftArr[next] != '.')
            preOrder(leftArr[next])
        if (rightArr[next] != '.')
            preOrder(rightArr[next])
    }

    fun inOrder(curr: Char) {
        val next = (curr.code - 'A'.code + 1)
        if (leftArr[next] != '.')
            inOrder(leftArr[next])
        print(curr)
        if (rightArr[next] != '.')
            inOrder(rightArr[next])
    }

    fun postOrder(curr: Char) {
        val next = (curr.code - 'A'.code + 1)
        if (leftArr[next] != '.')
            postOrder(leftArr[next])
        if (rightArr[next] != '.')
            postOrder(rightArr[next])
        print(curr)
    }

    fun solution() {
        preOrder('A')
        println()
        inOrder('A')
        println()
        postOrder('A')
        println()
    }
}

fun main() {
    Boj1991().solution()
}