package org.example

import DontDanceBehavior

class DecoyDuck: Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = MuteQuackBehavior(),
    danceBehavior = DontDanceBehavior()
) {
    override fun display() {
       println("I'm decoy duck")
    }
}