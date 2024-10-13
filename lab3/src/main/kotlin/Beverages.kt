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

open class Tea : Beverage("Tea") {
    override fun getCost() = 30.0
}

class MilkUlunTea : Tea() {
    override fun getDescription() = "Milk Ulun ${super.getDescription()}"
}

class ShuPuerTea : Tea() {
    override fun getDescription() = "ShuPuer ${super.getDescription()}"
}

class BubbleTea : Tea() {
    override fun getDescription() = "Bubble ${super.getDescription()}"
}

class MangoTea : Tea() {
    override fun getDescription() = "Mango  ${super.getDescription()}"
}

// Капуччино
open class Cappuccino : Coffee("Cappuccino") {
    override fun getCost(): Double = 80.0
}

class DoubleCappuccino : Cappuccino() {
    override fun getDescription() = "Double ${super.getDescription()}"
    override fun getCost(): Double = 120.0
}

// Латте
open class Latte : Coffee("Latte") {
    override fun getCost() = 90.0
}

class DoubleLatte : Latte() {
    override fun getDescription() = "Double ${super.getDescription()}"

    override fun getCost() = 130.0
}

// Молочный коктейль
class Milkshake(private val mSize: MilkshakeSize) : Beverage("Milkshake") {
    override fun getCost() = mSize.getCost()
    override fun getDescription(): String {
        val stringRes = when (mSize) {
            MilkshakeSize.SMALL -> "Small"
            MilkshakeSize.MEDIUM -> "Medium"
            MilkshakeSize.LARGE -> "Large"
        }
        return  "$stringRes ${super.getDescription()}"
    }
}

enum class MilkshakeSize(private val value: UInt) {
    SMALL(0U),
    MEDIUM(1U),
    LARGE(2U);

    fun getCost() = when (value) {
        SMALL.value -> 50.0
        MEDIUM.value -> 50.0
        LARGE.value -> 50.0
        else -> throw IllegalArgumentException("Incorrect size")
    }
}

/* todo Ввести стандартную и двойную порцию латте. Двойная порция стоит 130 рублей, стандартная – 90.
    Ввести стандартную (80) и двойную (120р) порции капучино
    Предлагать покупателям 4 сорта чая (цена не зависит от сорта чая). Названия сортов выберите на свое усмотрение.
    Предлагать маленькую (50 р), среднюю (60р) и большую (80р) порции молочных коктейлей*/
