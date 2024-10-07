import kotlin.test.assertEquals

class MockDisplay1(
    override val getInfo: () -> Double,
    override val informationDisplay: IInformationDisplay
) : IObserver<SWeatherInfo> {

    override fun update(data: SWeatherInfo) {
        assertEquals(counter,0)
        counter += 1
    }
}

class MockDisplay2(
    override val getInfo: () -> Double,
    override val informationDisplay: IInformationDisplay
) : IObserver<SWeatherInfo> {

    override fun update(data: SWeatherInfo) {
        assertEquals(counter,1)
        counter += 1
    }
}

class MockDisplay3(
    override val getInfo: () -> Double,
    override val informationDisplay: IInformationDisplay
) : IObserver<SWeatherInfo> {

    override fun update(data: SWeatherInfo) {
        assertEquals(counter,2)
        counter += 1
    }
}
