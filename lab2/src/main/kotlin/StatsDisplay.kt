
data class Sensors(
    val temperature: SensorStat = SensorStat("temperature"),
    val humidity: SensorStat = SensorStat("humidity"),
    val pressure: SensorStat = SensorStat("pressure"),
    val wind: SensorWindStats = SensorWindStats()
)

class StatsDisplay: IObserver<WeatherInfo> {
    private val mSensors = Sensors()

    private val informationDisplay = InformationDisplay()


    override fun update(data: WeatherInfo){
        updateShowWeatherData(data)
    }

    private fun updateShowWeatherData(data: WeatherInfo) =  with(mSensors){
        temperature.update(data.temperature)
        humidity.update(data.humidity)
        pressure.update(data.pressure)
        wind.update(data.windDirection, data.windSpeed)
        listOf(temperature, humidity, pressure).forEach {
            informationDisplay.display(
                listOf(
                    InfoItem("Min ${it.name}", it.getMin()),
                    InfoItem("Max ${it.name}", it.getMax()),
                    InfoItem("Average ${it.name}", it.getAverage()),
                )
            )
        }

        informationDisplay.display(
            listOf(
                InfoItem("Min speed", wind.getMinSpeed()),
                InfoItem("Max speed", wind.getMaxSpeed()),
                InfoItem("Average speed", wind.getAverageSpeed()),
            )
        )

        informationDisplay.display(
            listOf(
                InfoItem("Min direction", wind.getMinDirection()),
                InfoItem("Max direction", wind.getMaxDirection()),
                InfoItem("Average direction", wind.getAverageDirection()),
            )
        )
    }
}