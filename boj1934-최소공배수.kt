class Boj1934 {
    val a: Int
    val b: Int

    init {
        readln().split(' ').map { it.toInt() }.also {
            a = it[0]
            b = it[1]
        }
    }

    fun solution() {
        val lcm = lcm(a, b)

        println(lcm)
    }

    fun gcd(a: Int, b: Int): Int {
        var stand = if (a >= b) a else b

        while (stand > 1) {
            if (a % stand == 0 && b % stand == 0) {
                return stand
            }
            --stand
        }
        return 1
    }

    fun lcm(a: Int, b: Int): Int { 
        var aNum =  a
        var bNum = b
        var lcm = 1


        while (true) {
            var gcd = gcd(aNum, bNum)
            
            if (gcd == 1) {
                break
            }
            aNum /= gcd
            bNum /= gcd
            lcm *= gcd
        }
        return lcm * aNum * bNum
    }
}

fun main() {
    repeat(readln().toInt()) {
        Boj1934().solution()
    }
}