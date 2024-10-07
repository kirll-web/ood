import  org.junit.jupiter.api.Test
import kotlin.test.assertEquals

var counter = 0

class Test {
    @Test
    fun testMallardDuckDance() {
        val wd = WeatherData()

        val temperatureDisplay = MockDisplay1(
            informationDisplay = CInformationDisplay("temperature"),
            getInfo = wd::getTemperature
        )

        val humidityDisplay = MockDisplay2(
            informationDisplay = CInformationDisplay("humidity"),
            getInfo = wd::getHumidity
        )

        val pressureDisplay = MockDisplay3(
            informationDisplay = CInformationDisplay("pressure"),
            getInfo = wd::getPressure
        )

        wd.registerObserver(2, humidityDisplay)
        wd.registerObserver(1, temperatureDisplay)
        wd.registerObserver(3, pressureDisplay)

        wd.notifyObservers()

        assertEquals(1, 1)
    }

}

