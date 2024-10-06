import org.example.Duck

class RedheadDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = QuackBehavior(),
    danceBehavior = Dance(Dance.MINUET)
) {
    override fun display() {
        println("I'm readhead duck")
    }
}