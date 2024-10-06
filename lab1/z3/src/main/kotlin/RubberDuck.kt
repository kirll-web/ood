

class RubberDuck : Duck(
    quackBehavior = SqueakBehavior(),
    cbFly = {},
    cbDance = {}
) {
    override fun display() {
        println("I'm rubber duck")
    }
}