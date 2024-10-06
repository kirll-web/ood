package org.example

import MallardDuck
import ModelDuck
import playWithDuck
import RedheadDuck
import RubberDuck

fun main() {
    val mallardDuck = MallardDuck()
    playWithDuck(mallardDuck)

    val redheadDuck = RedheadDuck()
    playWithDuck(redheadDuck)

    val rubberDuck = RubberDuck()
    playWithDuck(rubberDuck)

    val modelDuck = ModelDuck()
    playWithDuck(modelDuck)

    val decoyDuck = DecoyDuck()
    playWithDuck(decoyDuck)
}