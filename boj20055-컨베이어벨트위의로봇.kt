class Boj20055 {
    val len: Int
    val limitCount: Int
    val belt: Array<IntArray>
    var ans = 0

    init {
        readln().split(' ').map { it.toInt() }.also {
            len = it[0]
            limitCount = it[1]
        }
        belt = Array(len * 2) { IntArray(2) { 0 } }
        readln().split(' ').map { it.toInt() }.also {
            for (i in belt.indices) {
                belt[i][0] = it[i]
            }
        }
    }

    fun solution() {
        while (true) {
            ++ans
//            println(belt.contentDeepToString())
            firstStep()
            secondStep()
            thirdStep()
            if (fourthStep())
                break
        }
        println(ans)
    }

    fun firstStep() {
        if (belt[len - 1][1] != 0)
            belt[len - 1][1] = 0

        val lastRobot = belt[belt.lastIndex][1]
        val lastHealth = belt[belt.lastIndex][0]

        for (i in belt.lastIndex - 1 downTo 0) {
            belt[i + 1][0] = belt[i][0]
            belt[i + 1][1] = belt[i][1]
        }
        belt[0][0] = lastHealth
        belt[0][1] = lastRobot
        if (belt[len - 1][1] != 0)
            belt[len - 1][1] = 0
    }

    fun secondStep() {
        for (i in belt.lastIndex - 1 downTo 0) {
            val isCurrBeltOnRobot = belt[i][1] == 0
            val isNextBeltOnRobot = belt[i + 1][1] == 0
            val isBroken = belt[i + 1][0] == 0

            if (!isCurrBeltOnRobot && isNextBeltOnRobot && !isBroken) {
                belt[i + 1][1] = 1
                belt[i + 1][0] -= 1
                belt[i][1] = 0
            }
        }
        if (belt[belt.lastIndex][1] != 0 && belt[0][1] == 0 && belt[0][0] != 0) {
            belt[0][1] = 1
            belt[0][0] -= 1
            belt[belt.lastIndex][1] = 0
        }
        if (belt[len - 1][1] != 0)
            belt[len - 1][1] = 0
    }

    fun thirdStep() {
        if (belt[0][0] != 0 && belt[0][1] == 0) {
            belt[0][1] = 1
            belt[0][0] -= 1
        }
    }

    fun fourthStep(): Boolean {
        return belt.count { it[0] == 0 } >= limitCount
    }
}

fun main() {
    Boj20055().solution()
}