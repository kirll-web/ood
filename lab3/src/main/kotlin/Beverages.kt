package org.example

class Beverages {
}

// Базовая реализация напитка, предоставляющая его описание
abstract class Beverage(
    private val description: String
) : IBeverage {
    override fun getDescription() = description
    abstract override fun getCost(): Double
}

// Кофе
open class Coffee(description: String = "Coffee") : Beverage(description) {
    override fun getCost(): Double = 60.0
}

// Капуччино
class Cappuccino : Coffee("Cappuccino") {
    override fun getCost(): Double = 80.0
}

// Латте
class Latte : Coffee("Latte") {
    override fun getCost() = 90.0
}

// Чай
class Tea : Beverage("Tea") {
    override fun getCost() = 30.0
}

// Молочный коктейль
class Milkshake : Beverage("Milkshake") {
    override fun getCost() = 80.0
}