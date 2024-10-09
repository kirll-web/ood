//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wdIn = WeatherData("WeatherDataIn")
    var wdOut = WeatherData("WeatherDataOut")

    var display = Display(wdIn, wdOut)
    wdOut.registerObserver(0, WeatherParam.TEMPERATURE to display)
    wdOut.registerObserver(1, WeatherParam.PRESSURE to display)

    val statsDisplay = StatsDisplay(wdIn, wdOut)

    wdIn.registerObserver(2, WeatherParam.TEMPERATURE to statsDisplay)
    wdIn.registerObserver(3,WeatherParam.HUMIDITY to statsDisplay)

    wdOut.setMeasurements(20.0, null, 360.0)
    wdOut.setMeasurements(null, 100.9, null , 70.0)

    wdIn.setMeasurements(3.0, 0.7)
    wdIn.setMeasurements(null, null, 761.0, 50.9, 70.0)
}