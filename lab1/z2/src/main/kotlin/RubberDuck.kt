import org.example.Duck
import org.example.FlyNoWay

class RubberDuck : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = SqueakBehavior(),
    danceBehavior = DontDanceBehavior()
) {
    override fun display() {
        println("I'm rubber duck")
    }
}