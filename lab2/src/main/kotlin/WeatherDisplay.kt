class WeatherDisplay : IInformationDisplay {
    override fun display(args: List<InfoItem>) {
        args.forEach {
            println("Current ${it.name} ${it.value}")
        }
        println("----------------")
    }
}