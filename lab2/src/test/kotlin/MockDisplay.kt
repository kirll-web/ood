class MockDisplay(
    override val informationDisplay: IInformationDisplay,
    override val getInfo: () -> Double,
    val removeObserver: (observer: IObserver<SWeatherInfo>) -> Unit,
) : IObserver<SWeatherInfo> {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mAcc: Double = 0.0
    private var mCountAcc: UInt = 0u

    override fun update(data: SWeatherInfo) {
        val info = getInfo()
        if (mMin > info) {
            mMin = info
        }
        if (mMax < info) {
            mMax = info
        }
        mAcc += info
        ++mCountAcc

        informationDisplay.display(mMin, mMax, mAcc / mCountAcc.toDouble())
        removeObserver(this)
    }
}