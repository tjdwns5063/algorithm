fun countScore(case: List<Char>, score: Int, ret: HashMap<Char, Int>) {
    when (score) {
        1 -> ret[case[0]] = ret.getOrDefault(case[0], 0) + 3
        2 -> ret[case[0]] = ret.getOrDefault(case[0], 0) + 2
        3 -> ret[case[0]] = ret.getOrDefault(case[0], 0) + 1
        4 -> return
        5 -> ret[case[1]] = ret.getOrDefault(case[1], 0) + 1
        6 -> ret[case[1]] = ret.getOrDefault(case[1], 0) + 2
        7 -> ret[case[1]] = ret.getOrDefault(case[1], 0) + 3
        else -> return
    }
}

fun makeName(ret: HashMap<Char, Int>, cases: List<String>): String {
    val answer = StringBuilder("")

    for (case in cases) {
        if (ret.getValue(case[0]) >= ret.getValue(case[1])) {
            answer.append(case[0])
        } else {
            answer.append(case[1])
        }
    }
    return answer.toString()
}

fun solution(survey: Array<String>, choices: IntArray): String {
    val cases = listOf("RT", "CF", "JM", "AN")
    val ret = HashMap<Char, Int>(8)
    for (i in cases.flatMap { it.toList() }) {
        ret[i] = 0
    }
    for (i in survey.indices) {
        countScore(survey[i].toList(), choices[i], ret)
    }
    return makeName(ret, cases)
}

fun main() {
    solution(arrayOf("TR", "RT", "TR"), intArrayOf(7, 1, 3))
}