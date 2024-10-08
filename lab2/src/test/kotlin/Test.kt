import  org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals


class TestDance {
    @Test
    fun testMallardDuckDance() {
        assertEquals(1, 1)
        val wd = WeatherData()
        val mockDisplay = MockDisplay(
            informationDisplay = CInformationDisplay("Mock"),
            getInfo = wd::getTemperature,
            removeObserver = { observer ->
                wd.removeObserver(observer)
            }
        )
        wd.registerObserver(mockDisplay)

        assertDoesNotThrow {
            wd.setMeasurements(3.0, 0.7, 760.0)
            wd.removeObserver(mockDisplay)
        }
    }

}

