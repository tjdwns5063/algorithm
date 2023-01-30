class divisorsNumberAndAddition {
    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0

        for (num in left..right) {
            var count = 0
            for (subNum in 1..num) {
                if (num % subNum == 0)
                    ++count
            }
            if (count % 2 == 0) {
                answer += num
            } else {
                answer -= num
            }
        }
        return answer
    }
}