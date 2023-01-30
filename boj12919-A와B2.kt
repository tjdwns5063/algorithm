class Boj12919 {
    val origin: String = readln()
    val target: String = readln()
    var ans = 0

    fun solution() {
        restoreLogic(StringBuilder(target))
        println(ans)
    }

    fun restoreLogic(targetCopy: StringBuilder) {
//        println(targetCopy)
        if (targetCopy.length == origin.length) {
            if (targetCopy.toString() == origin)
                ans = 1
            return
        }
        if (targetCopy[0] == 'B') {
            restoreLogic(StringBuilder(targetCopy.substring(1, targetCopy.length).reversed()))
        }
        if (targetCopy[targetCopy.lastIndex] == 'A') {
            restoreLogic(StringBuilder(targetCopy.substring(0, targetCopy.length - 1)))
        }
    }
}

fun main() {
    Boj12919().solution()
}