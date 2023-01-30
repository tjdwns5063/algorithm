class MatrixMulti {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) { 0 } }

        for (i in answer.indices) {
            for (j in answer[0].indices) {
                for (k in arr2.indices) {
                    println("i: $i j: $j k: $k")
                    answer[i][j] += arr1[i][k] * arr2[k][j]
                }
            }
        }
        answer.forEach { println(it.toList()) }
        return answer
    }
}

fun main() {
    MatrixMulti().solution(
        arrayOf(intArrayOf(80,90,60), intArrayOf(75,80,90), intArrayOf(90,95,65), intArrayOf(99,70,70)),
        arrayOf(intArrayOf(3,1), intArrayOf(3,8), intArrayOf(4,1))
    )
}