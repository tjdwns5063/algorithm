import kotlin.math.*

fun main() {
    val nums: IntArray = intArrayOf(0, 1, 100, 2, 50, 60, 3, 5, 6, 7, 8)
    
    val dpNPow2: IntArray = IntArray(nums.size) { 0 }
    val dpNLog2: IntArray = IntArray(nums.size) { 0 }
    
    // O(n^2)
    for (i in 1..nums.size-1) {
        // val curr = nums[i]
        for (j in 0..nums.size-1) {
            if (nums[i] > nums[j])
                dpNPow2[i] = max(dpNPow2[i], dpNPow2[j] + 1);
        }
    }
    println(dpNPow2.contentToString())

    //O(NlogN)
    var i = 2
    var j = 1
    dpNLog2[1] = nums[1]
    println(dpNLog2.contentToString())

    val upperBound: (Int, Int, Int, IntArray) -> Int = { start, end, target, arr ->
        var mid: Int
        var start = start
        var end = end

        while (start < end) {
            mid = (start + end) / 2

            if (arr[mid] < target) {
                start = mid + 1
            } else {
                end = mid
            }
        }
        end
    }
    var ans = 0
    while (i <= nums.size-1) {
        if (nums[i] > dpNLog2[j]) {
            dpNLog2[j + 1] = nums[i]
            ans = max(dpNLog2.sum(), ans)
            ++j
        } else {
            val idx = upperBound(1, j, nums[i], dpNLog2)
            dpNLog2[idx] = nums[i];
        }
        println(dpNLog2.contentToString());
        ++i
    }
    println(dpNLog2.contentToString());
    println(ans)
}
