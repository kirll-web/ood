import org.example.IDanceBehavior

class Dance(override val dance: String = ""): IDanceBehavior{
    override fun dance() {
        println("I am dancing $dance")
    }

    companion object {
        const val WALTZ = "waltz"
        const val MINUET = "minuet"
    }
}
//метод должен быть void