class Boj13305 {
    val n: Int
    val dists: LongArray
    val costs: LongArray

    init {
        n = readln().toInt()
        dists = readln().split(' ').map { it.toLong() }.toLongArray()
        costs = readln().split(' ').map { it.toLong() }.toLongArray()
    }
    fun solution() {
        // println(dists.contentToString())
        // println(costs.contentToString())
        var ans = 0L
        var currCost = costs[0]

        for (i in 0 until n-1) {
            if (currCost > costs[i])
                currCost = costs[i]
            ans += currCost * dists[i]
        }
        println(ans)
    }
}

fun main() {
    Boj13305().solution()
}