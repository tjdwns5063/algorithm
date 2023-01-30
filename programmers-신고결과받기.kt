fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
    val reportHash = HashMap<String, ArrayList<String>>()
    val reportedHash = HashMap<String, Int>()
    val newReport = report.toSet()

    for (id in id_list) {
        reportHash += Pair(id, ArrayList())
    }

    for (str in newReport) {
        val (to, from) = str.split(' ')
            reportHash[to]?.add(from)
    }

    for ((_, v) in reportHash) {
        for (name in v) {
            val value = reportedHash.getOrDefault(name, 0)
            reportedHash[name] = value + 1
        }
    }

    val answer = IntArray(id_list.size) { 0 }

    println(reportHash)
    println(reportedHash)

    for ((key, v) in reportedHash) {
        if (v >= k) {
            for (i in reportHash.keys) {
                if (reportHash[i]!!.contains(key))
                    ++answer[id_list.indexOf(i)]
            }
        }
    }
    return answer
}

fun main() {
    val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
    val report = arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi")
    val k = 2
    solution(id_list, report, k)
}