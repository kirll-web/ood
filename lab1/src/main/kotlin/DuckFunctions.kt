import org.example.Duck

fun DrawDuck(duck: Duck)
{
    duck.display()
}

fun playWithDuck(duck: Duck)
{
    DrawDuck(duck)
    duck.quack()
    duck.fly()
    duck.dance()
    duck.swim()
    println()
}