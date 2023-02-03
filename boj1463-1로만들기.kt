// time = 0.15sec
// memory = 128mb
// n = 1 <= n <= 1_000_000

import kotlin.math.*

class Boj1463 {
    val n = readln().toInt()
    val arr = IntArray(1_000_001) { 0 }

    fun solution() {
        arr[2] = 1
        arr[3] = 1

        for (i in 4..1_000_000) {
            if (i% 3 == 0 && i%2 ==0) {
                arr[i] = min(arr[i/3]+1, arr[i/2]+1)
            } else if (i % 3 == 0) {
                arr[i] = min(arr[i / 3] + 1, arr[i-1] + 1)
            } else if (i % 2 == 0) {
                arr[i] = min(arr[i / 2] + 1, arr[i-1]+1)
            } else {
                arr[i] = arr[i - 1] + 1
            }
        }
        println(arr[n])
    }
}

fun main() {
    Boj1463().solution()
}