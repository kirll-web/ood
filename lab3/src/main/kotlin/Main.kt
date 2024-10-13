package org.example

import java.util.UUID
import kotlin.reflect.full.primaryConstructor

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
Функциональный объект, создающий лимонную добавку
*/
fun makeLemon(quantity: UInt) = { beverage: IBeverage ->
    Lemon(beverage, quantity)
}


/*
Функция, возвращающая функцию, создающую коричную добавку
*/
fun makeCinnamon(): (b: IBeverage) -> Cinnamon = { b ->
    Cinnamon(b)
}


fun getAmount(name: String): UInt {
    var amount: UInt
    while (true) {
        println("Amount $name: ")
        try {
            amount = readln().toUInt()
            break
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }
    }
    return amount
}

fun getIceCubesType(): IceCubeType {
    var number: UInt
    var type: IceCubeType
    while (true) {
        println("Ice Cubes Type: 1 - Dry, 2 - Water ")
        try {
            number = readln().toUInt()

            type = when (number) {
                1U -> IceCubeType.Dry
                2U -> IceCubeType.Water
                else -> {
                    println("Incorrect input")
                    continue
                }
            }

            break
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }
    }
    return type
}


fun getSyrupType(): SyrupType {
    var number: UInt
    var type: SyrupType
    while (true) {
        println("Syrup Type: 1 - Chocolate, 2 - Maple ")
        try {
            number = readln().toUInt()

            type = when (number) {
                1U -> SyrupType.Chocolate
                2U -> SyrupType.Maple
                else -> {
                    println("Incorrect input")
                    continue
                }
            }

            break
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }
    }
    return type
}

fun getMilkshakeSize(): MilkshakeSize {
    var number: UInt
    var size: MilkshakeSize
    while (true) {
        println(" Milkshake size: 1 - Small, 2 - Medium, 3 - Large")
        try {
            number = readln().toUInt()

            size = when (number) {
                1U -> MilkshakeSize.SMALL
                2U ->MilkshakeSize.MEDIUM
                3U ->MilkshakeSize.LARGE

                else -> {
                    println("Incorrect input")
                    continue
                }
            }

            break
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }
    }
    return size
}

inline fun <reified T : Any> makeCondiment(vararg params: Any?): (b: IBeverage) -> T = { b ->
    val constructor =
        T::class.primaryConstructor ?: throw IllegalArgumentException("Нет доступного конструктора")

    constructor.call(b, *params)  // Создаём экземпляр, передавая аргументы
}


operator fun <Component, Decorator> Component.plus(decorator: (Component) -> Decorator): Decorator {
    return decorator(this)
}

fun dialogWithUser() {
    println("Type 1 for coffee, " +
            "2 for tea, " +
            "3 for Cappuccino, " +
            "4 for Latte, " +
            "5 for Milkshake," +
            " 6 - Double Cappuccino," +
            " 7 - Double Latte" +
            " 8 - ShuPuer Tea" +
            " 9 - Milk Ulun Tea" +
            " 10 - Bubble Tea" +
            " 11 - Mango Tea")
    var beverageChoice: UInt
    var beverage: IBeverage
    while (true) {
        try {
            beverageChoice = readln().toUInt()
            beverage = when (beverageChoice) {
                1U -> Coffee()
                2U -> Tea()
                3U -> Cappuccino()
                4U -> Latte()
                5U -> Milkshake(getMilkshakeSize())
                6U -> DoubleCappuccino()
                7U -> DoubleLatte()
                8U -> ShuPuerTea()
                9U -> MilkUlunTea()
                10U -> BubbleTea()
                11U -> MangoTea()

                else -> {
                    println("Incorrect input")
                    continue
                }
            }
            break
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }
    }

    var condimentChoice: Int
    while (true) {
        println("1 - Lemon, 2 - Cinnamon, 3 - IceCubes, 4 - ChocolateCrumbs, 5 - CoconutFlakes, 6 - Syrup, 0 - Checkout")

        try {
            condimentChoice = readln().toInt()
        } catch (ex: Exception) {
            println("Incorrect input")
            continue
        }


        when (condimentChoice) {
            1 -> beverage += makeLemon(getAmount("lemon"))
            2 -> beverage += makeCinnamon()
            3 -> beverage += makeCondiment<IceCubes>(getAmount("IceCubes"), getIceCubesType())
            4 -> beverage += makeCondiment<ChocolateCrumbs>(getAmount("ChocolateCrumbs"))
            5 -> beverage += makeCondiment<CoconutFlakes>(getAmount("CoconutFlakes"))
            6 -> beverage += makeCondiment<Syrup>(getSyrupType())

            0 -> break
        }
    }


    println("${beverage.getDescription()} , cost: ${beverage.getCost()}")
}

fun main() {
    dialogWithUser()
    println()
}