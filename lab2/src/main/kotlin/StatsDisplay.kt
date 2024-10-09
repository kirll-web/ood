
data class SensorsIn(
    val temperature: SensorStat = SensorStat("temperature"),
    val humidity: SensorStat = SensorStat("humidity"),
    val pressure: SensorStat = SensorStat("pressure"),
)


data class SensorsOut(
    val temperature: SensorStat = SensorStat("temperature"),
    val humidity: SensorStat = SensorStat("humidity"),
    val pressure: SensorStat = SensorStat("pressure"),
    val wind: SensorWindStats = SensorWindStats()
)

class StatsDisplay(
    private val weatherDataIn: IObservable<WeatherInfo>,
    private val weatherDataOut: IObservable<WeatherInfo>,
) : IObserver<WeatherInfo> {
    private val mSensorsIn = SensorsIn()
    private val mSensorsOut = SensorsOut()

    private val informationDisplay = InformationDisplay()


    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>){
        when (observable) {
            weatherDataIn -> {
                updateShowWeatherDataIn(data)
            }
            weatherDataOut -> {
                updateShowWeatherDataOut(data)
            }
            else -> Unit
        }
    }

    private fun updateShowWeatherDataIn(data: WeatherInfo) =  with(mSensorsIn){
        temperature.update(data.temperature)
        humidity.update(data.humidity)
        pressure.update(data.pressure)
        listOf(temperature, humidity, pressure).forEach {
            informationDisplay.display(
                listOf(
                    InfoItem("Min ${it.name}", it.getMin()),
                    InfoItem("Max ${it.name}", it.getMin()),
                    InfoItem("Average ${it.name}", it.getAverage()),
                )
            )
        }
    }

    private fun updateShowWeatherDataOut(data: WeatherInfo)  = with(mSensorsOut){
        temperature.update(data.temperature)
        humidity.update(data.humidity)
        pressure.update(data.pressure)
        wind.update(data.windDirection, data.windSpeed)

        listOf(
            temperature,
            humidity,
            pressure
        ).forEach {
            informationDisplay.display(
                listOf(
                    InfoItem("Min ${it.name}", it.getMin()),
                    InfoItem("Max ${it.name}", it.getMin()),
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