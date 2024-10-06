import org.example.Duck

class MallardDuck: Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = QuackBehavior(),
    danceBehavior = DanceBehavior("waltz")
) {
    override fun display() {
        println("I'm mallard duck")
    }
}