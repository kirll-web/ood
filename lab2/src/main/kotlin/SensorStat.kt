class SensorStat(
    val name: String
) {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mAcc: Double = 0.0
    private var mCountAcc: UInt = 0u


    fun update(data: Double) {
        if (mMin > data) {
            mMin = data
        }
        if (mMax < data) {
            mMax = data
        }
        mAcc += data
        ++mCountAcc
    }

    fun getMin() = mMin
    fun getMax() = mMax
    fun getAverage() = mAcc / mCountAcc.toDouble()
}