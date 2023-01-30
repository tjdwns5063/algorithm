fun solution(numbers: IntArray): Int {
    val nonExistNumbers = intArrayOf(0,1,2,3,4,5,6,7,8,9).filter { !numbers.contains(it) }
    return nonExistNumbers.fold(0) { acc, i ->
        acc + i
    }
}

fun main() {
    println(solution(intArrayOf(1,2,3,4,5,6,7)))
}