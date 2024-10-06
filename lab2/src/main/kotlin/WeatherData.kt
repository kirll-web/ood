import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

data class SWeatherInfo(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var pressure: Double = 0.0,
    var windDirection: Double = 0.0,
    var windSpeed: Double,
)

class CDisplay : IObserver<SWeatherInfo> {
    override fun update(data: SWeatherInfo) {
        informationDisplay.display(
            listOf(
                InfoItem("temperature", data.temperature),
                InfoItem("humidity", data.humidity),
                InfoItem("pressure", data.pressure),
                InfoItem("wind direction", data.windDirection),
                InfoItem("wind speed", data.windDirection)
            )
        )
    }

    override val informationDisplay: IInformationDisplay = CWeatherDisplay()
    override val getInfo: () -> Double = { 0.0 }
}

class StatsDisplay(
    override val getInfo: () -> Double,
    override val informationDisplay: IInformationDisplay
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

        informationDisplay.display(
            listOf(
                InfoItem("Min", mMin),
                InfoItem("Max", mMax),
                InfoItem("Average", mAcc / mCountAcc.toDouble())
            )
        )
    }
}

class StatsWindDirectionDisplay(
    override val getInfo: () -> Double,
    override val informationDisplay: IInformationDisplay
) : IObserver<SWeatherInfo> {
    private var mMin: Double = Double.POSITIVE_INFINITY
    private var mMax: Double = Double.NEGATIVE_INFINITY
    private var mSumSin: Double = 0.0
    private var mSumCos: Double = 0.0
    private var mCountAcc: UInt = 0u


    override fun update(data: SWeatherInfo) {
        val info = getInfo()
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
                InfoItem("Average", average
            )
        ))
    }

}


class WeatherData : Observable<SWeatherInfo>() {
    private var mTemperature: Double = 0.0
    private var mHumidity: Double = 0.0
    private var mPressure: Double = 760.0
    private var mWindSpeed: Double = 0.0
    private var mWindDirection: Double = 0.0

    // Температура в градусах Цельсия
    fun getTemperature(): Double {
        return mTemperature
    }

    // Относительная влажность (0...100)
    fun getHumidity(): Double {
        return mHumidity
    }

    // Атмосферное давление (в мм.рт.ст)
    fun getPressure(): Double {
        return mPressure
    }

    fun getWindDirection(): Double {
        return mWindDirection
    }

    fun getWindSpeed(): Double {
        return mWindSpeed
    }

    private fun measurementsChanged() {
        notifyObservers()
    }

    fun setMeasurements(
        temp: Double,
        humidity: Double,
        pressure: Double,
        windSpeed: Double,
        windDirection: Double
    ) {
        mHumidity = humidity
        mTemperature = temp
        mPressure = pressure
        mWindSpeed = windSpeed
        mWindDirection = windDirection

        measurementsChanged()
    }

    override fun getChangedData() = SWeatherInfo(
        temperature = getTemperature(),
        humidity = getHumidity(),
        pressure = getPressure(),
        windSpeed = getWindSpeed(),
        windDirection = getWindDirection()
    )
}

class CInformationDisplay(
    private val name: String,
) : IInformationDisplay {
    override fun display(args: List<InfoItem>) {
        args.forEach {
            println("${it.name} $name ${it.value}")
        }
        println("----------------")
    }
}

class CWeatherDisplay : IInformationDisplay {
    override fun display(args: List<InfoItem>) {
        args.forEach {
            println("Current ${it.name} ${it.value}")
        }
        println("----------------")
    }
}