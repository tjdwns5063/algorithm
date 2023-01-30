fun main() {
    val str = readln()
    val croatia_alpha = listOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")
    var ans = 0
    var idx = 0

    for (alpha in croatia_alpha) {
        idx = str.indexOf(alpha, idx)
        if  (idx < 0)
            continue
        while (idx != -1) {
            ++ans
            idx = str.indexOf(alpha, idx + alpha.length)
        }
    }
    println(str.length - ans)
}