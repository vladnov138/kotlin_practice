import kotlin.math.max

fun findMaxPeriod(n: Int): Int {
    var maxRemainder = 0
    var maxD = 3
    for (d in 3 until 1000) {
        val remainders = ArrayList<Int>()
        var remainder = n

        while (remainder != 0 && !remainders.contains(remainder)) {
            remainders.add(remainder)
            remainder = (remainder * 10) % d
        }

        if (remainder != 0) {
            maxRemainder = max(maxRemainder, remainders.size)
            maxD = if (maxRemainder == remainders.size) d else maxD
        }
    }
    return maxD
}

fun main() {
    val n = 1
    println("Делитель с максимальным периодом: ${findMaxPeriod(n)}")
}