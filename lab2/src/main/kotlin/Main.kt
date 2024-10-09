//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wd = WeatherData("WeatherDataIn")

    var display = Display()
    wd.registerObserver(0, display)

    val statsDisplay = StatsDisplay()

    wd.registerObserver(2, statsDisplay)

    wd.setMeasurements(3.0, 0.7, 760.0, 50.9, 30.0)
    wd.setMeasurements(4.0, 0.8, 761.0, 50.9, 70.0)
}