import org.example.Duck

class MallardDuck: Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = QuackBehavior(),
    danceBehavior = Dance(Dance.WALTZ)
) {
    override fun display() {
        println("I'm mallard duck")
    }
}