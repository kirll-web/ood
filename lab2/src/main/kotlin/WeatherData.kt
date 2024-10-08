
data class WeatherInfo(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var pressure: Double = 0.0,
    var windDirection: Double = 0.0,
    var windSpeed: Double,
)


class WeatherData(override val name: String) : Observable<WeatherInfo>() {
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

    override fun getChangedData() = WeatherInfo(
        temperature = getTemperature(),
        humidity = getHumidity(),
        pressure = getPressure(),
        windSpeed = getWindSpeed(),
        windDirection = getWindDirection()
    )
}



