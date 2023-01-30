class BinarySearch {
    fun binarysearch(arr: IntArray, target: Int, start: Int, end: Int) {
        if (start >= end) {
            println("0")
            return
        }
        val mid = (start + end) / 2
        println("mid: $mid")

        if (arr[mid] > target) {
            binarysearch(arr, target, start, mid - 1)
        } else if (arr[mid] < target) {
            binarysearch(arr, target, mid + 1, end)
        } else {
            println("1")
            return
        }
    }
}

fun main() {
    val arr = intArrayOf(1,4,3,2,5,6,9,8,7)
    arr.sort()
    println(arr.contentToString())
    BinarySearch().binarysearch(arr, 10, 0, 8)
}