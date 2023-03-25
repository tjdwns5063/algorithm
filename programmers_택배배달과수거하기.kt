import kotlin.math.*;

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0

        val totalDel = deliveries.sum()
        val totalPickups = pickups.sum()

        println(totalDel)
        println(totalPickups)
        var idx = n-1
        while (idx >= 0 && deliveries[idx] == 0 && pickups[idx] == 0)
            --idx
        while (idx >= 0) {
            var currDel = if (totalDel > cap) cap else totalDel
            var currPick = if (totalPickups > cap) cap else totalPickups

            for (i in idx downTo 0) {
                currDel -= deliveries[i]
                if (currDel < 0) {
                    deliveries[i] = -currDel;
                    break
                } else {
                    deliveries[i] = 0;
                }
            }

            for (i in idx downTo 0) {
                currPick -= pickups[i]
                if (currPick < 0) {
                    pickups[i] = -currPick;
                    break
                } else {
                    pickups[i] = 0;
                }
            }
            answer += (idx + 1) * 2
            while (idx >= 0 && deliveries[idx] == 0 && pickups[idx] == 0)     
                --idx
        }
        // println(deliveries.contentToString())
        // println(pickups.contentToString())


        println(answer)
        return answer
    }
}

fun main() {
    val cap = 4;
    val n = 5;
    val deliver = intArrayOf(1, 0, 3, 1, 2);
    val pickup = intArrayOf(0, 3, 0, 4, 0);
    Solution().solution(cap, n, deliver, pickup);
}