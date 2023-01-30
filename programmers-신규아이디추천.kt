class Solution {
    private fun level1(str: String) = str.lowercase()

    private fun level2(str: String): String {
        return str.filter {
            it in 'a'..'z' ||
                    (it == '-' || it == '_' || it =='.') ||
                    it.isDigit()
        }
    }

    private fun level3(str: String): String {
        var flag = 0
        val newStr = StringBuilder("")

        for (ch in str) {
            if (ch == '.') {
                flag += 1
                if (flag == 1)
                    newStr.append(ch)
            } else {
                flag = 0
                newStr.append(ch)
            }
        }
        return newStr.toString()
    }

    private fun level4(str: String) = str.trim('.')

    private fun level5(str: String) = if (str.isEmpty()) str.plus('a') else str

    private fun level6(str: String): String {
        if (str.length < 16) return str

        var newString = str.substring(0, 15)
        if (newString.last() == '.')
            newString = newString.dropLast(1)
        return newString
    }

    private fun level7(str: String): String {
        if (str.length > 2) return str

        var strBuiler = StringBuilder(str)

        while (strBuiler.length < 3) {
            strBuiler.append(str.last())
        }
        return strBuiler.toString()
    }

    fun solution(new_id: String): String {
        var answer = level1(new_id)
        answer = level2(answer)
        answer = level3(answer)
        answer = level4(answer)
        answer = level5(answer)
        answer = level6(answer)
        answer = level7(answer)

        return answer
    }
}

fun main() {
    println(Solution().solution("123_.def"))
}
