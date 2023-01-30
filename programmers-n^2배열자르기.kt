class CuttingNNArray {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        var answer: IntArray = intArrayOf()
        val origin = Array(n * n) { if (it / n + 1 > it + 1) it + 1 else it/n+1  }
        println(origin.toList())
//        val second = origin.flatMap { it.toList() }.subList(left.toInt(), right.toInt() + 1).toIntArray()
        return answer
    }
}

fun main() {
    CuttingNNArray().solution(3,2,5).also { println(it.toList()) }
}