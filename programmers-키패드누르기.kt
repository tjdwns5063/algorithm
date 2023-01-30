import kotlin.math.abs

fun getLength(curr: Int, number: Int): Int {
    val currIdx = when (curr) {
        -1 -> 9
        0 -> 10
        -2 -> 11
        else -> curr - 1
    }

    val currNumber = when (number) {
        0 -> 10
        else -> number - 1
    }
    val currPos = Pair(currIdx / 3, currIdx % 3)
    val numberPos = Pair(currNumber / 3, currNumber % 3)
    return abs(currPos.first - numberPos.first) + abs(currPos.second - numberPos.second)
}

fun solution(numbers: IntArray, hand: String): String {
    val answer = StringBuilder("")
    var currRight = -2
    var currLeft = -1

    numbers.forEach {
        when (it) {
            1, 4, 7 -> {
                currLeft = it
                answer.append("L")
            }
            3, 6, 9 -> {
                currRight = it
                answer.append("R")
            }
            else -> {
                val leftLength = getLength(currLeft, it)
                val rightLength = getLength(currRight, it)

                if (rightLength > leftLength) {
                    currLeft = it
                    answer.append("L")
                } else if (rightLength < leftLength) {
                    currRight = it
                    answer.append("R")
                } else {
                    if (hand == "right") {
                        currRight = it
                        answer.append("R")
                    } else {
                        currLeft = it
                        answer.append("L")
                    }
                }
            }
        }
    }
    return answer.toString()
}

fun main() {
    solution(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right").also { println(it) }
}