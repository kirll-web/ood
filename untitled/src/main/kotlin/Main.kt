package org.example
enum class Type {
    ALL,
    REQUIRED,
    CATALog
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val list: List<Type> = listOf(Type.ALL, Type.REQUIRED)
    val newList = when {
        list.contains(Type.ALL) -> list.minus(Type.ALL)
        else -> list.plus(Type.ALL)
    }
    println(list)
   println(newList)
}