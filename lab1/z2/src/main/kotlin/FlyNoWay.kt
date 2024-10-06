package org.example

class FlyNoWay : IFlyBehavior
{
    override var danceBehavior: IDanceBehavior? = null
    override var counter = 0
    override fun fly(behavior: IDanceBehavior) {
        danceBehavior = behavior
    }
}