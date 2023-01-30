import kotlin.math.max

class Boj16987 {
    val n: Int
    val eggs: Array<IntArray>
    var ans = 0

    init {
        n = readln().toInt()
        eggs = Array(n) {
            readln().split(' ').map { it.toInt() }.toIntArray()
        }
    }

    fun dfs(currEgg: Int) {
        if (currEgg == n) {
//            println("${eggs.contentDeepToString()}")
            ans = max(ans, eggs.count { it[0] <= 0 })
            return
        }
        var flag: Boolean = false
        for (i in eggs.indices) {
            /* 1. 계란 서로 박치기
             * 2. 깨진 계란 카운트
             * 3. 현재 계란 내려놓고 다음 계란 들기
             * 4. 재귀 끝나면, 계란 다시 원상복구
             */
            if (i == currEgg || eggs[i][0] <= 0)
                continue
            if (eggs[currEgg][0] <= 0)
                dfs(currEgg + 1)
            else {
                flag = true
                eggs[currEgg][0] -= eggs[i][1]
                eggs[i][0] -= eggs[currEgg][1]
                dfs(currEgg + 1)
                eggs[currEgg][0] += eggs[i][1]
                eggs[i][0] += eggs[currEgg][1]
            }
        }
        // 5. 더이상 깰 계란이 없는 경우 맨 끝 계란을 집게 해줌
        if (!flag)
            dfs(currEgg + 1)
    }

    fun solution() {
        dfs(0)
        println(ans)
    }
}

fun main() {
    Boj16987().solution()
}