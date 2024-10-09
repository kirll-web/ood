class Display(
    private val weatherDataIn: IObservable<WeatherInfo, WeatherParam>,
    private val weatherDataOut: IObservable<WeatherInfo, WeatherParam>
) : IObserver<WeatherInfo, WeatherParam> {
    private val informationDisplay: IInformationDisplay = WeatherDisplay()
    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo, WeatherParam>) {
        println("${observable.name}:")

        when (observable) {
            weatherDataIn -> {
                informationDisplay.display(
                    listOf(
                        InfoItem("temperature", data.temperature),
                        InfoItem("humidity", data.humidity),
                        InfoItem("pressure", data.pressure)
                    )
                )
            }
            weatherDataOut -> {
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