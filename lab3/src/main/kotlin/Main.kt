package org.example

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


inline fun <reified T : Any> makeCondiment(vararg params: Any?): (IBeverage) -> T = { base ->
    val constructor =
        T::class.primaryConstructor ?: throw IllegalArgumentException("Нет доступного конструктора")

    constructor.call(base, *params)  // Создаём экземпляр, передавая аргументы
}


operator fun <Component, Decorator> Component.plus(decorator: (Component) -> Decorator): Decorator {
    return decorator(this)
}

fun dialogWithUser() {
    println("Type 1 for coffee or 2 for tea")
    var beverageChoice = 0
    beverageChoice = readln().toInt()

    lateinit var beverage: IBeverage

    if (beverageChoice == 1) {
        beverage = Coffee()
    } else if (beverageChoice == 2) {
        beverage = Tea()
    } else {
        return
    }
    var condimentChoice: Int = 0
    while (true) {
        println("1 - Lemon, 2 - Cinnamon, 0 - Checkout")
        condimentChoice = readln().toInt()

        if (condimentChoice == 1) {
            beverage += makeLemon(2U)
        } else if (condimentChoice == 2) {
            beverage += makeCinnamon()
        } else if (condimentChoice == 0) {
            break
        } else {
            return
        }
    }


    println("${beverage.getDescription()} , cost: ${beverage.getCost()}")
}

fun main() {
    dialogWithUser()
    println()

    val latte = Latte()
    // добавляем корицы
    val cinnamon = Cinnamon((latte))
    // добавляем пару долек лимона
    val lemon = Lemon(cinnamon, 2U)
    // добавляем пару кубиков льда
    val iceCubes = IceCubes(beverage = lemon, quantity = 2U, type = IceCubeType.Dry)
    // добавляем 2 грамма шоколадной крошки
    val beverage1 = ChocolateCrumbs(iceCubes, 2U)

    // Выписываем счет покупателю
    println("${beverage1.getDescription()} , cost: ${beverage1.getCost()}")

    val beverage2 = ChocolateCrumbs(
        IceCubes(
            Lemon(Cinnamon(Latte()), 2U), 2U, IceCubeType.Dry
        ),
        2U
    )

    // Выписываем счет покупателю
    println("${beverage2.getDescription()} , cost: ${beverage2.getCost()}")

    // Подробнее рассмотрим работу MakeCondiment и оператора <<
    val lemon2 = makeCondiment<Lemon>(2U)
    // iceCubes - функция, добавляющая "3 кусочка льда" к любому напитку
    val iceCubes3 = makeCondiment<IceCubes>(3U, IceCubeType.Water)

    val tea = Tea()

    // декорируем чай двумя дольками лимона и тремя кусочками льда
    val lemonIceTea = iceCubes3(lemon2(tea))
    println("${lemonIceTea.getDescription()} , cost: ${lemonIceTea.getCost()}")
    val oneMoreLemonIceTea = Tea() +
            makeCondiment<Lemon>(2U) +
            makeCondiment<IceCubes>(3U, IceCubeType.Water)
    println("${oneMoreLemonIceTea.getDescription()} , cost: ${oneMoreLemonIceTea.getCost()}")

    val beverage3 = Latte() + makeCondiment<Cinnamon>() + makeCondiment<Lemon>(2U) +
            makeCondiment<IceCubes>(2U, IceCubeType.Dry)
    println("${beverage3.getDescription()} , cost: ${beverage3.getCost()}")

    val beverage4 = Milkshake() +
            makeCondiment<Syrup>(SyrupType.Maple) +
            makeCondiment<CoconutFlakes>(8U)

    // Выписываем счет покупателю
    println("${beverage4.getDescription()} , cost: ${beverage4.getCost()}")
}