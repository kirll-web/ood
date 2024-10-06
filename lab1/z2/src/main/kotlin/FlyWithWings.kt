import org.example.IDanceBehavior
import org.example.IFlyBehavior


class FlyWithWings: IFlyBehavior {
    override var danceBehavior: IDanceBehavior? = null
    override var counter: Int = 0
    override fun fly(behavior: IDanceBehavior) {
        danceBehavior = behavior
        counter += 1
        println("I'm flying with wings!! Count wings: $counter")

        danceBehavior?.let {
            if(counter % 2 == 0 && danceBehavior != null) {
                danceBehavior?.dance()
            }
        }
    }
}
//сделать так, чтобы утки умеющие летать каждый второй вылет танцевали