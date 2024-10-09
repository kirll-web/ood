class Display: IObserver<WeatherInfo> {
    private val informationDisplay: IInformationDisplay = WeatherDisplay()
    override fun update(data: WeatherInfo) {
        informationDisplay.display(
            listOf(
                InfoItem("temperature", data.temperature),
                InfoItem("humidity", data.humidity),
                InfoItem("pressure", data.pressure)
            )
        )
    }
}