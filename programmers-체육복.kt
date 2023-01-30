fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    var answer = 0
    var lented = IntArray(n) { 0 }

    for (i in lented.indices) {
        lented[i] = when {
            reserve.contains(i + 1) -> 2
            else -> 1
        }
    }

    for (person in lost) {
        lented[person - 1] -= 1
    }
    println(lented.toList())

    for (i in lented.indices) {
        if (lented[i] == 2) {
            if (i-1 >= 0 && lented[i-1] == 0) {
                lented[i-1] += 1
                lented[i] -= 1
            } else if (i+1 < lented.size && lented[i+1] == 0) {
                lented[i+1] += 1
                lented[i] -= 1
            }
        }
    }
    println(lented.toList())
    lented.forEach {
        if (it > 0)
            answer += 1
    }
    return answer
}

fun main() {
    println(solution(3, intArrayOf(2), intArrayOf(2)))
}