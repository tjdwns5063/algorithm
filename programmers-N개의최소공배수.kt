class LeastCommonMultiple {
    fun gcd(a: Int, b: Int): Int {
        var num1 = b
        var num2 = a % b
        while (num2 != 0) {
            val prev = num2
            num2 = num1 % num2
            num1 = prev
        }
        return num1
    }

    fun solution(arr: IntArray): Int {
        var answer = 0

        var gcd = gcd(arr[0], arr[1])
        var lcm = arr[0] * arr[1] / gcd
        for (i in 2 until arr.size) {
            gcd = gcd(lcm, arr[i])
            lcm = lcm * arr[i] / gcd
        }
        answer = lcm
        return answer
    }
}

fun main() {
    LeastCommonMultiple().solution(intArrayOf(1,2,3))
}