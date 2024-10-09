
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class SensorWindStats {
    private val mSpeedDirs = mutableListOf<Pair<Double, Double>>()
    private var mMinSpeed: Double = Double.POSITIVE_INFINITY
    private var mMaxSpeed: Double = Double.NEGATIVE_INFINITY
    private var mMinDirection: Double = Double.POSITIVE_INFINITY
    private var mMaxDirection: Double = Double.NEGATIVE_INFINITY
    private var mAverageSpeed = 0.0
    private var mAverageDirection = 0.0

    fun update(degree: Double, speed: Double) {
        if (mMinSpeed > speed) {
            mMinSpeed = speed
        }
        if (mMaxSpeed < speed) {
            mMaxSpeed = speed
        }
        if (mMinDirection > degree) {
            mMinDirection = degree
        }
        if (mMaxDirection < degree) {
            mMaxDirection = degree
        }

        mSpeedDirs.add( speed to degree )
        average()
    }

    private fun average(): Pair<Double, Double>  {
        var sinSum = 0.0
        var cosSum = 0.0

        mSpeedDirs.forEach {
            val speed = it.first
            val dir = it.second

            sinSum += speed * sin(dir * (Math.PI / 180))
            cosSum += speed * cos(dir * (Math.PI / 180))
        }

        sinSum /= mSpeedDirs.size
        cosSum /= mSpeedDirs.size

        mAverageSpeed = sqrt(cosSum * cosSum + sinSum * sinSum)
        mAverageDirection = (((atan2(sinSum, cosSum) * 180 / Math.PI) + 360) % 360)

        return mAverageSpeed to mAverageDirection
    }

    fun getMinSpeed() = mMinSpeed
    fun getMaxSpeed() = mMaxSpeed
    fun getAverageSpeed() = mAverageSpeed

    fun getMinDirection() = mMinDirection
    fun getMaxDirection() = mMaxDirection
    fun getAverageDirection() = mAverageDirection
}

