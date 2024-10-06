import org.example.IDanceBehavior

class DanceBehavior(private val dance: String = ""): IDanceBehavior{
    override var counter = 0
    override fun dance() {
        counter += 1
        println("I am dancing $dance")
    }
}