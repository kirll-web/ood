package org.example

import org.example.org.example.IQuackBehavior

open class Duck(
    var flyBehavior: IFlyBehavior,
    var quackBehavior: IQuackBehavior,
    var danceBehavior: IDanceBehavior
) {

    open fun display(){}

    fun quack() = quackBehavior.quack()

    fun swim() = println("I'm swimming")

    fun fly()  {
        flyBehavior.fly(danceBehavior)
    }

    fun dance() = danceBehavior.dance()

    fun setFly(behavior: IFlyBehavior) {
        this.flyBehavior = behavior
    }

    fun setDance(behavior: IDanceBehavior) {
        this.danceBehavior = behavior
    }
}