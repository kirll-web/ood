import org.example.Duck

class RedheadDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = QuackBehavior(),
    danceBehavior = DanceBehavior("minuet")
) {
    override fun display() {
        println("I'm readhead duck")
    }
}