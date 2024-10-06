//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wdIn = WeatherData("WeatherDataIn")
    var wdOut = WeatherData("WeatherDataOut")

    var display = CDisplay()
    wdIn.registerObserver(0, display)
    wdOut.registerObserver(0, display)

    val temperatureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("temperature"),
        getInfo = listOf(wdIn::getTemperature, wdOut::getTemperature)
    )

    val humidityDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("humidity"),
        getInfo = listOf(wdIn::getHumidity, wdOut::getHumidity),
    )

    var pressureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("pressure"),
        getInfo = listOf(wdIn::getPressure, wdOut::getPressure),
    )

    val windSpeed = StatsDisplay(
        informationDisplay = CInformationDisplay("wind  speed"),
        getInfo = listOf(wdOut::getWindSpeed),
    )

    val windDirection = StatsWindDirectionDisplay(
        informationDisplay = CInformationDisplay("wind direction"),
        getInfo = listOf(wdOut::getWindSpeed),
    )

    wdIn.registerObserver(2, temperatureDisplay)
    wdIn.registerObserver(3, humidityDisplay)
    wdIn.registerObserver(1, pressureDisplay)
    wdOut.registerObserver(4, windDirection)
    wdOut.registerObserver(5, windSpeed)

    wdIn.setMeasurements(3.0, 0.7, 760.0, 50.9, 30.0)
    wdIn.setMeasurements(4.0, 0.8, 761.0, 50.9, 70.0)

    wdOut.setMeasurements(20.0, 40.7, 360.0, 50.9, 30.0)
    wdOut.setMeasurements(40.0, 60.8, 551.0, 100.9, 70.0)
}