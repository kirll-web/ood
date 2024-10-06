class DecoyDuck: Duck(
    quackBehavior = MuteQuackBehavior(),
    cbFly = {},
    cbDance = {}
) {
    override fun display() {
       println("I'm decoy duck")
    }
}