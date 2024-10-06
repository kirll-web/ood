
class RedheadDuck : Duck(
    quackBehavior = QuackBehavior(),
    cbDance = dance("Minuet"),
    cbFly = fly(dance("Minuet"))
) {
    override fun display() {
        println("I'm readhead duck")
    }
}