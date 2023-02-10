class Boj7785 {
    val n: Int
    val map: MutableMap<String, Boolean>

    init {
        n = readln().toInt()
        map = mutableMapOf()

        repeat(n) {
            readln().split(' ').also {
                if (it[1] == "enter") {
                    map[it[0]] = true
                } else {
                    map[it[0]] = false
                }
            }
        }
    }

    fun solution() {
        // println(map)
        var ans = mutableListOf<String>()

        for ((key, value) in map) {
            if (value) {
                ans += key
            }
        }
        ans.sortDescending()
        ans.forEach {
            println(it)
        }
    }
}

fun main() {
    Boj7785().solution()
}