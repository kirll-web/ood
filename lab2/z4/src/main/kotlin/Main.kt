package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wdIn = WeatherData("WeatherDataIn") //todo: должно быть два объекта weatherData
    var wdOut = WeatherData("WeatherDataOut") //todo: должно быть два объекта weatherData

    var display = CDisplay(0)
    wdIn.registerObserver(display)
    wdOut.registerObserver(display)

    //todo: индикаторы должны отображать информацию сразу двух weatherData
    var temperatureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("temperature"),
        getInfo = listOf(wdIn::getTemperature, wdOut::getTemperature),
        token = 4,
        removeObserver = { observer -> wdIn.removeObserver(observer.token) }
    )

    var humidityDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("humidity"),
        getInfo = listOf(wdIn::getHumidity, wdOut::getHumidity),
        token = 2,
        removeObserver = { observer -> wdIn.removeObserver(observer.token) }
    )

    var pressureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("pressure"),
        getInfo = listOf(wdIn::getPressure, wdOut::getPressure),
        token = 3,
        removeObserver = { observer -> wdIn.removeObserver(observer.token) }
    )

    wdIn.registerObserver(temperatureDisplay)
    wdIn.registerObserver(humidityDisplay)
    wdIn.registerObserver(pressureDisplay)

    wdOut.registerObserver(temperatureDisplay)
    wdOut.registerObserver(humidityDisplay)
    wdOut.registerObserver(pressureDisplay)

    wdIn.setMeasurements(3.0, 0.7, 760.0)
    wdIn.setMeasurements(4.0, 0.8, 761.0)

    wdOut.setMeasurements(300.0, 300.0, 1060.0)
    wdOut.setMeasurements(450.0, 2000.8, 1061.0)
}