class InformationDisplay : IInformationDisplay<InfoItem> {
    override fun display(args: List<InfoItem>) {
        args.forEach {
            println("${it.name} ${it.value}")
        }
        println("----------------")
    }
}