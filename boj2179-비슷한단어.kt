// time=2sec
// memory=128mb
// n= 2 <= 20000

import kotlin.math.*

class Boj2179 {
    val n: Int
    var strs: Array<String>
    var ans: MutableList<Pair<String, Int>>

    init {
        n = readln().toInt()
        strs = Array(n) { readln() }
        ans = mutableListOf()
    }

    fun solution() {
       strs.forEachIndexed { i, v ->
            ans += v to i
       }
       ans = ans.sortedBy{it.first}.toMutableList()
    //    println(ans)
       val cntList = IntArray(n) { 0 }
       for (i in 0 until ans.size - 1) {
           var idx = 0
            var cnt = 0
            while (idx < ans[i].first.length && idx < ans[i+1].first.length) {
                if (ans[i].first[idx] == ans[i + 1].first[idx])
                    ++cnt
                else
                    break
                ++idx
            }
            cntList[i] = if (cntList[i] < cnt) cnt else cntList[i]
            cntList[i+1] = if (cntList[i+1] < cnt) cnt else cntList[i+1]
       }

       val mx = cntList.maxOf { it }
       var answer:MutableList<Pair<String, Int>> = mutableListOf()
       for (i in cntList.indices) {
            if (cntList[i] == mx) {
                answer += ans[i]
            }
       }
        val li = answer.sortedWith(compareBy({it.second}, {it.first}))
        li.filter{li[0].first[0] == it.first[0]}.also{
            println(it[0].first)
            val s = it[0].first
            // println(it)
            it.forEachIndexed { i,v ->
                if (i > 0 && v.first[0] == s[0]) {
                    println(v.first)
                    return
                }
            }
        }   
    }
}

fun main() {
    Boj2179().solution()
}