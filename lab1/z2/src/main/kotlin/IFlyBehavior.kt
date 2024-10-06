package org.example

interface IFlyBehavior {
    open var danceBehavior: IDanceBehavior?
    open var counter: Int
    fun fly(behavior: IDanceBehavior)
}
