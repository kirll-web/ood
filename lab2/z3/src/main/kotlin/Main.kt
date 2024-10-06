package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wd = WeatherData()

    var display = CDisplay(0)
    wd.registerObserver(display)

    var temperatureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("temperature"),
        getInfo = wd::getTemperature,
        token = 4,
        removeObserver = { observer -> wd.removeObserver(observer.token) })

    var humidityDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("humidity"),
        getInfo = wd::getHumidity,
        token = 2,
        removeObserver = { observer -> wd.removeObserver(observer.token) }
    )

    var pressureDisplay = StatsDisplay(
        informationDisplay = CInformationDisplay("pressure"),
        getInfo = wd::getPressure,
        token = 3,
        removeObserver = { observer -> wd.removeObserver(observer.token) }
    )

    wd.registerObserver(temperatureDisplay)
    wd.registerObserver(humidityDisplay)
    wd.registerObserver(pressureDisplay)

    wd.setMeasurements(3.0, 0.7, 760.0)
    wd.setMeasurements(4.0, 0.8, 761.0)

    wd.removeObserver(4)

    wd.setMeasurements(10.0, 0.8, 761.0)
    wd.setMeasurements(-10.0, 0.8, 761.0)
}