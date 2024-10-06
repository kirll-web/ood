open class Duck(
    quackBehavior: IQuackBehavior,
    private val cbDance: () -> Unit,
    private val cbFly: () -> Unit
) {
    private var mQuackBehavior: IQuackBehavior = quackBehavior

    open fun display() {}

    fun quack() = mQuackBehavior.quack()

    fun swim() = println("I'm swimming")

    fun dance() {
        cbDance()
    }

    fun fly() {
        cbFly()
    }

}