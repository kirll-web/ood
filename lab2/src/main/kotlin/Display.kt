class Display(
    private val weatherDataIn: IObservable<WeatherInfo>,
    private val weatherDataOut: IObservable<WeatherInfo>
) : IObserver<WeatherInfo> {
    private val informationDisplay: IInformationDisplay = WeatherDisplay()
    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        println("${observable.name}:")

        when {
            observable == weatherDataIn -> {
                informationDisplay.display(
                    listOf(
                        InfoItem("temperature", data.temperature),
                        InfoItem("humidity", data.humidity),
                        InfoItem("pressure", data.pressure)
                    )
                )
            }

            observable == weatherDataOut -> {
                informationDisplay.display(
                    listOf(
                        InfoItem("temperature", data.temperature),
                        InfoItem("humidity", data.humidity),
                        InfoItem("pressure", data.pressure),
                        InfoItem("windDirection", data.windDirection),
                        InfoItem("windSpeed", data.windSpeed),
                    )
                )
            }

            else -> Unit
        }
    }
}