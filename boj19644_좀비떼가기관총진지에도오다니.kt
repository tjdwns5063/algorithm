import java.util.*
import kotlin.math.*
import java.math.*
import java.io.*

class Boj19644 {
    val n: Int
    val machineGun: IntArray
    var bomb: Int
    val zombies: IntArray
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val possibleDamage: IntArray
    val effectedBomb: Deque<Pair<Int, Int>> // first: 폭탄이 터진 지점, second: 영향 미치는 마지막 지점

    init {
        n = br.readLine().toInt()
        machineGun = br.readLine().split(' ').map { it.toInt() }.toIntArray()
        bomb = br.readLine().toInt()
        zombies = IntArray(3_000_001) { 0 }
        
        repeat(n) {
            zombies[it + 1] = br.readLine().toInt()
        }

        possibleDamage = IntArray(3_000_001) { 0 }
        effectedBomb = LinkedList()
    }

    fun solution() {
        var weaponDist = machineGun[0]
        val weaponDamage = machineGun[1]

        // 각 위치 마다 쏠 수 있는 최대 데미지 계산
        for (i in 1..n) {
            possibleDamage[i] = if (i < weaponDist) {
                i * weaponDamage
            }  else {
                weaponDist * weaponDamage
            }
        }

        for (i in 1..n) {
            // 수류탄을 던진 지점이 있고 그 영향이 현재 위치에 영향을 미치지 않으면 수류탄 제거
            if (effectedBomb.isNotEmpty() && !isEffectedBomb(effectedBomb.peek().second, i)) {
                effectedBomb.poll()
            }
            // 현재 영향을 미치는 수류탄만큼 현재 데미지를 줄임
            var currDamage = possibleDamage[i] - (effectedBomb.size * weaponDamage)

            // 현재 맨 앞의 좀비가 현재 데미지로 잡을 수 없으면 폭탄 사용
            if (zombies[i] > currDamage) {
                // 사용가능한 수류탄이 있으면 사용하고 영향을 미치는 수류탄 리스트에 추가, 불가능하면 "NO" 출력 후 종료.
                if (bomb > 0) {
                    effectedBomb += i to i + weaponDist - 1
                    bomb -= 1
                } else {
                    println("NO")
                    return
                }
            }
        }
        println("YES")
    }

    fun isEffectedBomb(lastEffectedIdx: Int, curr: Int): Boolean {
        return lastEffectedIdx >= curr
    }
}

fun main() {
    Boj19644().solution()
}