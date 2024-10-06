import  org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class TestDance {
    val duck = MallardDuck()

    @Test
    fun firstFlyWithDance() {
        duck.fly()
        assertEquals(duck.flyBehavior.counter, 1)
        assertEquals(duck.danceBehavior.counter, 0) // odd
        assertNotEquals(duck.danceBehavior.counter, 1)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 2)
        assertEquals(duck.danceBehavior.counter, 1) // even
        assertNotEquals(duck.danceBehavior.counter, 2)
        assertNotEquals(duck.danceBehavior.counter, 0)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 3)
        assertEquals(duck.danceBehavior.counter, 1) // odd
        assertNotEquals(duck.danceBehavior.counter, 2)
        assertNotEquals(duck.danceBehavior.counter, 3)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 4)
        assertEquals(duck.danceBehavior.counter, 2) // even
        assertNotEquals(duck.danceBehavior.counter, 1)
        assertNotEquals(duck.danceBehavior.counter, 3)
    }

    @Test
    fun flyBehaviorsDanceBehaviorIsDontDance() {
        duck.setDance(DontDanceBehavior())
        duck.fly()
        assertEquals(duck.danceBehavior is DontDanceBehavior, true)
        assertEquals(duck.flyBehavior.counter, 1)
        assertEquals(duck.danceBehavior.counter, 0)
        assertNotEquals(duck.danceBehavior.counter, 1)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 2)
        assertEquals(duck.danceBehavior.counter, 0)
        assertNotEquals(duck.danceBehavior.counter, 2)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 3)
        assertEquals(duck.danceBehavior.counter, 0)
        assertNotEquals(duck.danceBehavior.counter, 2)

        duck.fly()
        assertEquals(duck.flyBehavior.counter, 4)
        assertEquals(duck.danceBehavior.counter, 0)
        assertNotEquals(duck.danceBehavior.counter, 1)
    }
}

