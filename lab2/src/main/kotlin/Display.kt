class Display : IObserver<WeatherInfo, WeatherParameter> {
    override fun update (
        name: String,
        data: WeatherInfo,
        changedParameter: WeatherParameter
    ) {
        println("$name:")
        informationDisplay.display(
            listOf(
                InfoItem("temperature", data.temperature),
                InfoItem("humidity", data.humidity),
                InfoItem("pressure", data.pressure),
                InfoItem("wind direction", data.windDirection),
                InfoItem("wind speed", data.windDirection)
            )
        )
    }

    override val informationDisplay: IInformationDisplay = WeatherDisplay()
    override val getInfo: List<() -> Double> = emptyList()
}
