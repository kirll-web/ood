data class Sensorы(
    SensorStats temperatureStats;
    SensorStats pressureStats;
    SensorStats humidityStats;
    WindStats windStats;
)

class StatsDisplay(
    override val getInfo: List<() -> Double>,
    override val informationDisplay: IInformationDisplay
) : IObserver<WeatherInfo> {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mAcc: Double = 0.0
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
            mAcc += info
            ++mCountAcc


            informationDisplay.display(
                listOf(
                    InfoItem("Min", mMin),
                    InfoItem("Max", mMax),
                    InfoItem("Average", mAcc / mCountAcc.toDouble())
                )
            )
        }

    }

}