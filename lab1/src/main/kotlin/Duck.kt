package org.example

import org.example.org.example.IQuackBehavior

open class Duck(
    val flyBehavior: IFlyBehavior,
    val quackBehavior: IQuackBehavior,
    val danceBehavior: IDanceBehavior
) {
    private var mQuackBehavior: IQuackBehavior = quackBehavior
    private var mFlyBehavior: IFlyBehavior = flyBehavior
    private var mDanceBehavior: IDanceBehavior = danceBehavior

    open fun display(){}

    fun quack() = mQuackBehavior.quack()

    fun swim() = println("I'm swimming")

    fun fly() =  mFlyBehavior.fly()

    fun dance() = mDanceBehavior.dance()

    fun setFlyBehavior(flyBehavior: IFlyBehavior) {
        mFlyBehavior = flyBehavior
    }
}
