package org.example

fun main() {
    val wd = WeatherData()

    val display = CDisplay()
    wd.registerObserver(display)

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

    wd.registerObserver(temperatureDisplay)
    wd.registerObserver(humidityDisplay)
    wd.registerObserver(pressureDisplay)

    wd.setMeasurements(3.0, 0.7, 760.0)
    wd.setMeasurements(4.0, 0.8, 761.0)

    wd.removeObserver(temperatureDisplay)

    wd.setMeasurements(10.0, 0.8, 761.0)
    wd.setMeasurements(-10.0, 0.8, 761.0)
}