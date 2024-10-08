
data class WeatherInfo(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var pressure: Double = 0.0
)


class WeatherData(override val name: String) : Observable<WeatherInfo>() {
    private var mTemperature: Double = 0.0
    private var mHumidity: Double = 0.0
    private var mPressure: Double = 760.0

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

    private fun measurementsChanged() {
        notifyObservers()
    }

    fun setMeasurements(
        temp: Double,
        humidity: Double,
        pressure: Double
    ) {
        mHumidity = humidity
        mTemperature = temp
        mPressure = pressure

        measurementsChanged()
    }

    override fun getChangedData() = WeatherInfo(
        temperature = getTemperature(),
        humidity = getHumidity(),
        pressure = getPressure()
    )
}



