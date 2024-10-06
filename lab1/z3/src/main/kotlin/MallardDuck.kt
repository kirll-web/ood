
class MallardDuck: Duck(
    quackBehavior = QuackBehavior(),
    cbFly = fly(dance("Walc")),
    cbDance = dance("Walc")
) {
    override fun display() {
        println("I'm mallard duck")
    }
}