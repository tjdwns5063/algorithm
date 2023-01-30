fun groupWorldChecker(str: String): Int {
    val wordArr = IntArray(26) { 0 } // 알파벳 개수 만큼 배열 생성

    var currVal: Char? = null
    for (i in str.indices) {
        if (currVal == str[i])
            continue
        wordArr[str[i] - 'a'] += 1
        currVal = str[i]
    }
    return if (wordArr.maxOf { it } <= 1) 1 else 0 // 배열의 최대값이 1 이하면 1을 아니면 0을 반환
}

fun main() {
    var ans = 0
    repeat(readln().toInt()) {
        ans += groupWorldChecker(readln())
    }
    println(ans)
}