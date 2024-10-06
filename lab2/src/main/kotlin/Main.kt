//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wdIn = WeatherData("WeatherDataIn")
    var wdOut = WeatherData("WeatherDataOut")

    var display = CDisplay()
    wdIn.registerObserver(7, display)
    wdOut.registerObserver(8, display)

    //todo: индикаторы должны отображать информацию сразу двух weatherData
    var temperatureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("temperature"),
        getInfo = listOf(wdIn::getTemperature, wdOut::getTemperature)
    )

    var humidityDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("humidity"),
        getInfo = listOf(wdIn::getHumidity, wdOut::getHumidity),
    )

    var pressureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("pressure"),
        getInfo = listOf(wdIn::getPressure, wdOut::getPressure),
    )

    wdIn.registerObserver(2,temperatureDisplay)
    wdIn.registerObserver(1, humidityDisplay)
    wdIn.registerObserver(4, pressureDisplay)

    wdOut.registerObserver(2, temperatureDisplay)
    wdOut.registerObserver(3, humidityDisplay)
    wdOut.registerObserver(10, pressureDisplay)

    wdIn.setMeasurements(3.0, 0.7, 760.0)
    wdIn.setMeasurements(4.0, 0.8, 761.0)

    wdOut.setMeasurements(300.0, 300.0, 1060.0)
    wdOut.setMeasurements(450.0, 2000.8, 1061.0)
}