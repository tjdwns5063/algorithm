class Boj1620 {
    val n: Int
    val m: Int
    val map: MutableMap<Int, String>
    val map2: MutableMap<String, Int>

    init {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }

        map = mutableMapOf()
        map2 = mutableMapOf()

        repeat(n) {
            val name = readln()

            map[it+1] = name
            map2[name] = it+1
        }
    }

    fun solution() {
        repeat(m) {
            val q = readln()

            if (q.toIntOrNull() != null) {
                println(map[q.toInt()])
            } else {
                println(map2[q])
            }
        }
    }
}

fun main() {
    Boj1620().solution()
}