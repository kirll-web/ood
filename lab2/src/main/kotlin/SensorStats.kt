class SensorStats  {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mAcc: Double = 0.0
    private var mCountAcc: UInt = 0u

    fun Update(newValue: Double)
    {
        if (mMin > newValue)
        {
            mMin = newValue;
        }
        if (mMax < newValue)
        {
            mMax = newValue
        }
        mAcc += newValue
        ++mCountAcc
    }

    fun min(): Double {
        return mMin
    }

    fun max(): Double {
        return mMax
    }

    fun Average(): Double  {
        return mAcc / mCountAcc.toDouble()
    }
};