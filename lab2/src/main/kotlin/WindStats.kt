import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class WindStats {
    private val mSpeedsDirections = mutableListOf<Pair<Double, Double>>()
    fun update(degree: Double, speed: Double) {
        mSpeedsDirections.add(speed to degree)
    }

    fun average(): Pair<Double, Double>  {
        var sinSum = 0.0
        var cosSum = 0.0

        mSpeedsDirections.forEach {
            val speed = it.first
            val direction = it.second

            sinSum += speed * sin(direction * (Math.PI / 180))
            cosSum += speed * cos(direction * (Math.PI / 180))
        }

        sinSum /= mSpeedsDirections.size
        cosSum /= mSpeedsDirections.size

        return sqrt(
            cosSum * cosSum + sinSum * sinSum
        ) to ((atan2(sinSum, cosSum) * 180.0 / Math.PI) + 360.0) * 360.0
    }
}