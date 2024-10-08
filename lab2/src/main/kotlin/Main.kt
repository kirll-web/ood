//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var wdIn = WeatherData("WeatherDataIn")
    var wdOut = WeatherData("WeatherDataOut")

    var display = Display(wdIn, wdOut)
    wdIn.registerObserver(0, display)
    wdOut.registerObserver(0, display)

    val statsDisplay = StatsDisplay(wdIn, wdOut)

    wdIn.registerObserver(2, statsDisplay)
    wdOut.registerObserver(3, statsDisplay)

    wdIn.setMeasurements(3.0, 0.7, 760.0)
    wdIn.setMeasurements(4.0, 0.8, 761.0)

    wdOut.setMeasurements(20.0, 40.7, 360.0)
    wdOut.setMeasurements(40.0, 60.8, 551.0)
}