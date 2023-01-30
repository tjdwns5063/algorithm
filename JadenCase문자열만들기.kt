class JadenCaseString {
    fun solution(s: String): String {
        var answer = StringBuilder("")
        for (i in s.indices) {
            if (i == 0 && !s[i].isDigit()) {
                answer.append(s[i].uppercase())
            } else if (i > 0 && s[i - 1] == ' ') {
                answer.append(s[i].uppercase())
            } else {
                answer.append(s[i].lowercase())
            }
        }
        println(answer.toString())
        return answer.toString()
    }
}

fun main() {
    JadenCaseString().solution("for the last week")
}