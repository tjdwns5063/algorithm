import java.lang.Thread.sleep
import kotlin.math.abs

class ExpectedDraw {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        var repeat = n
        var left = if (a < b) a else b
        left = if (left % 2 == 0) left else left + 1
        var right = if (b > a) b else a
        right = if (right % 2 == 0) right else right + 1

        if (left == right) return 1

        while (repeat != 1) {
//            println("left: $left right: $right repeat: $repeat")
            var ret = 1
            if (left in 1..repeat / 2 && right in repeat / 2 + 1..repeat) {
                while (ret != repeat) {
                    ret *= 2
                    answer += 1
                }
                break
            } else {
                left = if (left > repeat/2) left - repeat / 2 else left
                right = if (right > repeat/2) right - repeat / 2 else right
                repeat /= 2
            }
        }
        return answer
    }
}

fun main() {
    ExpectedDraw().solution(32,3,6).also { println(it) }
}