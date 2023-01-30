class IterateBinaryConvert {
    fun solution(s: String): IntArray {
        var str = StringBuilder(s)
        val answer = intArrayOf(0, 0)

        while (true) {
            str = StringBuilder(str.filter {
                if (it == '0')
                    answer[1] += 1
                it == '1'
            })
            println("str: $str")
            var length = str.length
            str = StringBuilder("")
            while (length != 0) {
                str.append(length % 2)
                length /= 2
            }
            str = str.reverse().also { println(it) }
            answer[0] += 1
            if (str.toString() == "1")
                break
        }
        println(answer.toList())
        return answer
    }
}

fun main() {
    IterateBinaryConvert().solution("01110")
}