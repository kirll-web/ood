package org.z1

enum class test {
    ERROR1,
    ERROR2,
    ERROR3
}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   val errors1 = arrayOf(test.ERROR1, test.ERROR2)
    val error2 = arrayOf(test.ERROR3)
    val errors3 = arrayOf(*errors1, *error2)
    println("output")

    errors3.forEach {
        println(it)
    }
}