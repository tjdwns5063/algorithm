fun solution(sizes: Array<IntArray>): Int {
    var answer = 0
    val reordered = sizes.onEach { if (it[0] < it[1]) {
        val temp = it[0]
        it[0] = it[1]
        it[1] = temp
    } }
    answer = reordered.maxOf { it[0] } * reordered.maxOf { it[1] }
    print(answer)
    return answer
}

fun main() {
    solution(arrayOf(intArrayOf(60,50), intArrayOf(30,70), intArrayOf(60,30), intArrayOf(80,40)))
}