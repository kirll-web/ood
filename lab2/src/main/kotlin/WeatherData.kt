
enum class WeatherParam {
    TEMPERATURE,
    HUMIDITY,
    PRESSURE,
    WIND_DIRECTION,
    WIND_SPEED
}

class WeatherData(override val name: String) : Observable<WeatherInfo, WeatherParam>() {
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

    private fun measurementsChanged(changedParams: List<WeatherParam>) {
        notifyObservers(changedParams)
    }

    fun setMeasurements(
        temperature: Double? = null,
        humidity: Double? = null,
        pressure: Double? = null,
        windSpeed: Double? = null,
        windDirection: Double? = null
    ) {
        val changedParam = mutableListOf<WeatherParam>()
        humidity?.let {
            mHumidity = humidity
            changedParam.add(WeatherParam.HUMIDITY)
        }
        temperature?.let {
            mTemperature = temperature
            changedParam.add(WeatherParam.TEMPERATURE)
        }
        pressure?.let {
            mPressure = pressure
            changedParam.add(WeatherParam.PRESSURE)
        }
        windSpeed?.let {
            mWindSpeed = windSpeed
            changedParam.add(WeatherParam.WIND_SPEED)
        }
        windDirection?.let {
            mWindDirection = windDirection
            changedParam.add(WeatherParam.WIND_DIRECTION)
        }

        measurementsChanged(changedParam.toList())
    }

    override fun getChangedData() = WeatherInfo(
        temperature = getTemperature(),
        humidity = getHumidity(),
        pressure = getPressure(),
        windSpeed = getWindSpeed(),
        windDirection = getWindDirection()
    )
}



