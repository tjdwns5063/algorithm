class RotateParentheses {
    fun checkParentheses(parentheses: ArrayList<Char>): Int {
        val lst = arrayListOf<Char>()
        for (i in parentheses.lastIndex downTo 0) {
            lst += parentheses[i]
            if (lst.size > 1) {
                if ((lst[lst.lastIndex] == '[' && lst[lst.lastIndex - 1] == ']') ||
                    (lst[lst.lastIndex] == '{' && lst[lst.lastIndex - 1] == '}') ||
                    (lst[lst.lastIndex] == '(' && lst[lst.lastIndex - 1] == ')')) {
                    lst.removeLast()
                    lst.removeLast()
                }
            }
        }
        if (lst.isEmpty())
            return 1
        return 0
    }

    fun solution(s: String): Int {
        var answer: Int = 0
        val lst = ArrayList<Char>()

        for (ch in s) {
            lst += ch
        }
        for (i in lst.indices) {
            answer += checkParentheses(lst)
            val ch = lst.removeFirst()
            lst += ch
        }
        return answer
    }
}

fun main() {
    RotateParentheses().solution(	"[)(]").also { println(it) }
}