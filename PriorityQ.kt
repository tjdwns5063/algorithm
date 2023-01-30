import java.util.PriorityQueue

fun main() {
    val pq = PriorityQueue<Int>()
    val pq2 = PriorityQueue { p1: Pair<Int, Int>, p2: Pair<Int, Int> ->
        if (p1.second > p2.second) 1 else if (p1.second == p2.second) 0 else -1
    }

    pq += 5
    pq += 1
    pq += 3

//    pq2 += Pair(1,4)
//    pq2 += Pair(2,3)
//    pq2 += Pair(3,2)
    while (true) {
        println(pq2.peek())
    }
    /* remove = 실패 시, NoSuchElementException 발생
     * poll = 실패 시, null 반환
     * add = 실패 시, IllegalStateException 발생
     * offer = 실패 시, false 반환
     * peek = 실패 시, null 반환
     */
}