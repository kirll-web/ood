/*
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class StatsWindDisplay(
    override val getInfo: List<() -> Double>,
) : IObserver<WeatherInfo> {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mSumSin: Double = 0.0
    private var mSumCos: Double = 0.0
    private var mCountAcc: UInt = 0u


    override fun update(name: String, data: WeatherInfo) {
        getInfo.forEach {
            println("$name:")
            val info = it()
            if (mMin > info) {
                mMin = info
            }
            if (mMax < info) {
                mMax = info
            }

            val rad = Math.toRadians(info)
            mSumSin += sin(rad)
            mSumSin += cos(rad)
            ++mCountAcc

            val average = (Math.toDegrees(atan2(mSumSin, mSumCos)) + 360) % 360

            informationDisplay.display(
                listOf(
                    InfoItem("Min", mMin),
                    InfoItem("Max", mMax),
                    InfoItem(
                        "Average", average
                    )
                )
            )
        }
    }

    override val informationDisplay: IInformationDisplay = InformationDisplay()
}*/
