

fun drawDuck(duck: Duck)
{
    duck.display()
}

fun playWithDuck(duck: Duck)
{
    drawDuck(duck)
    duck.quack()
    duck.fly()
    duck.fly()
    duck.fly()
    duck.dance()
    println()
}

fun dance(dance: String): () -> Unit {

    fun letsDance() {
        println("I'am dancing dance $dance")
    }
    return ::letsDance
}

fun fly(cbDance: (() -> Unit)? = null): () -> Unit {
    var countFlyings = 0

    fun letsFly() {
        println("I'am flying. Counter: ${++countFlyings}")

        if(countFlyings % 2 == 0) cbDance?.let { it() }
    }

    return ::letsFly
}