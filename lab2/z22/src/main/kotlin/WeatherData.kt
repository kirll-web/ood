package org.example

data class SWeatherInfo(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var pressure: Double = 0.0
)

class CDisplay : IObserver<SWeatherInfo> {
    override fun update(data: SWeatherInfo) {
        informationDisplay.display(data.temperature, data.humidity, data.pressure)
    }

    override val informationDisplay: IInformationDisplay = CWeatherDisplay()
    override val getInfo: () -> Double = { 0.0 }
}

class StatsDisplay(
    override val informationDisplay: IInformationDisplay,
    val removeObserver: (observer: IObserver<SWeatherInfo>) -> Unit,
    override val getInfo: () -> Double,
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


class WeatherData : Observable<SWeatherInfo>() {
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

    fun setMeasurements(temp: Double, humidity: Double, pressure: Double) {
        mHumidity = humidity
        mTemperature = temp
        mPressure = pressure

        measurementsChanged()
    }

    override fun getChangedData() = SWeatherInfo(
        temperature = getTemperature(),
        humidity = getHumidity(),
        pressure = getPressure()
    )

}

class CInformationDisplay(
    private val name: String,
): IInformationDisplay{
    override fun display(v1: Double, v2: Double, v3: Double) {
        println("Max $name $v1")
        println("Min $name $v2")
        println("Average $name $v3")
        println("----------------")
    }
}

class CWeatherDisplay: IInformationDisplay{
    override fun display(v1: Double, v2: Double, v3: Double) {
        println("Current Temp $v1")
        println("Current Hum $v2")
        println("Current Pressure $v3")
        println("----------------")
    }
}