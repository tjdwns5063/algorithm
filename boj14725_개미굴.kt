import java.util.*
import kotlin.math.*

class Boj14725 {
    val n: Int
    val lineList: MutableList<List<String>>

    init {
        n = readln().toInt()
        lineList = mutableListOf<List<String>>()

        repeat(n) {
            lineList += readln().split(' ').let {
                it.subList(1, it.size)
            }
        }
    }

    fun solution() {
        val comparator = object: Comparator<List<String>> {
        override fun compare(a: List<String>, b: List<String>): Int {
                var idx = 0
                
                while (idx < a.size && idx < b.size) {
                    if (a[idx] != b[idx])
                        return a[idx].compareTo(b[idx])
                    
                    ++idx
                }
                return a.size - b.size
            }
        }
        
        val visited = HashMap<Pair<String, Int>, Boolean>()
        val sorted = lineList.sortedWith(comparator)
        val ans = StringBuilder()

        // println(sorted)
        var currRoot = sorted[0][0]
        for (list in sorted) {
            if (currRoot != list[0]) {
                currRoot = list[0]
                visited.clear()
            }
            for ((idx, str) in list.withIndex()) {
                if (visited.containsKey(str to idx))
                    continue
                else {
                    val appendix = StringBuilder()
                    for (i in 0 until idx) {
                        appendix.append("--")
                    }
                    ans.append("$appendix$str\n")
                    visited[str to idx] = true
                }
            }
        }
        print(ans)
        // println(sorted)
    }
}

fun main() {
    Boj14725().solution()
}