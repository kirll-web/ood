import org.example.Duck
import org.example.FlyNoWay

class ModelDuck: Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = QuackBehavior(),
    danceBehavior = DontDanceBehavior()
) {
    override fun display() {
        println("I'm model duck")
    }
}