//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val wd = WeatherData()

    val display = CDisplay()
    wd.registerObserver(0, display)

    val temperatureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("temperature"),
        getInfo = wd::getTemperature
    )

    val humidityDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("humidity"),
        getInfo = wd::getHumidity
    )

    val pressureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("pressure"),
        getInfo = wd::getPressure
    )

    val windSpeed = StatsDisplay(
        informationDisplay = CInformationDisplay("wind  speed"),
        getInfo = wd::getWindSpeed
    )

    val windDirection = StatsWindDirectionDisplay(
        informationDisplay = CInformationDisplay("wind direction"),
        getInfo = wd::getWindDirection
    )

    wd.registerObserver(2, temperatureDisplay)
    wd.registerObserver(4, humidityDisplay)
    wd.registerObserver(1, pressureDisplay)
    wd.registerObserver(5, windSpeed)
    wd.registerObserver(3, windDirection)

    wd.setMeasurements(3.0, 0.7, 760.0, 50.9, 30.0)
    wd.setMeasurements(4.0, 0.8, 761.0, 50.9, 70.0)

    wd.removeObserver(4)

    wd.setMeasurements(10.0, 0.8, 761.0, 50.9, 80.0)
    wd.setMeasurements(-10.0, 0.8, 761.0, 50.9, 90.0)
}