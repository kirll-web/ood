import org.example.DecoyDuck
import  org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class TestDance {
    @Test
    fun testMallardDuckDance() {
        val duck = MallardDuck()
        assertEquals(duck.danceBehavior.dance, Dance.WALTZ)
        assertNotEquals(duck.danceBehavior.dance, Dance.MINUET)
        assertNotEquals(duck.danceBehavior.dance, "")
    }

    @Test
    fun testRubberDuckDance() {
        val duck = RubberDuck()
        assertEquals(duck.danceBehavior.dance, "")
        assertNotEquals(duck.danceBehavior.dance, Dance.MINUET)
        assertNotEquals(duck.danceBehavior.dance, Dance.WALTZ)
    }

    @Test
    fun testRedheadDuckDance() {
        val duck = RedheadDuck()
        assertEquals(duck.danceBehavior.dance, Dance.MINUET)
        assertNotEquals(duck.danceBehavior.dance, Dance.WALTZ)
        assertNotEquals(duck.danceBehavior.dance, "")
    }

    @Test
    fun testDecoyDuck() {
        val duck = DecoyDuck()
        assertEquals(duck.danceBehavior.dance, "")
        assertNotEquals(duck.danceBehavior.dance, Dance.MINUET)
        assertNotEquals(duck.danceBehavior.dance, Dance.WALTZ)
    }

    @Test
    fun testModelDuckDance() {
        val duck = ModelDuck()
        assertEquals(duck.danceBehavior.dance, "")
        assertNotEquals(duck.danceBehavior.dance, Dance.MINUET)
        assertNotEquals(duck.danceBehavior.dance, Dance.WALTZ)
    }

}

