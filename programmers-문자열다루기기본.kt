class StrHandle1 {
    fun solution(s: String): Boolean {
        if (s.length != 4 && s.length != 6)
            return false
        for (ch in s) {
            if (!ch.isDigit())
                return false
        }
        return true
    }
}

fun main() {
    println(StrHandle1().solution("a234"))
}