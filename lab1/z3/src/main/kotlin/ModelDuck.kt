

class ModelDuck: Duck(
    quackBehavior = QuackBehavior(),
    cbFly = {},
    cbDance = {}
) {
    override fun display() {
        println("I'm model duck")
    }
}