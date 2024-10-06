package org.example

import DontDance

class DecoyDuck: Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = MuteQuackBehavior(),
    danceBehavior = DontDance()
) {
    override fun display() {
       println("I'm decoy duck")
    }
}
