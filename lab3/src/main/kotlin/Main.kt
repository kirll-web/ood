package org.example

import java.nio.file.Files.move
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.createInstance
import kotlin.reflect.jvm.javaConstructor


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
/*
Возвращает функцию, декорирующую напиток определенной добавкой

Параметры шаблона:
	Condiment - класс добавки, конструктор которого в качестве первого аргумента
				принимает IBeveragePtr&& оборачиваемого напитка
	Args - список типов прочих параметров конструктора (возможно, пустой)
*/
/*template <typename Condiment, typename... Args>
auto MakeCondiment(const Args&...args) {
    // Возвращаем функцию, декорирующую напиток, переданный ей в качестве аргумента
    // Дополнительные аргументы декоратора, захваченные лямбда-функцией, передаются
    // конструктору декоратора через make_unique
    return [=](auto && b) {
    // Функции make_unique передаем b вместе со списком аргументов внешней функции
    return make_unique<Condiment>(forward<decltype(b)>(b), args...)
    }
}*/

/*inline fun <reified T: CondimentDecorator> makeCondiment(vararg args: Any, co: () -> T): (Any) -> Any {

    return { base ->
        (T::class as Any).javaClass.constructors.first().newInstance(base, args)
    }
}*/

inline fun <reified T> makeCondiment(vararg args: Any): (IBeverage) -> T {
    return { base ->
        val type = T::class.java

        // Создаем объект типа T
        var beverageList = arrayOf(base, *args)
        val obj = type.constructors[0].newInstance(*beverageList)
        obj as T
    }
}


inline operator fun <Component, Decorator> Component.plus(decorate: (Component) -> Decorator): Decorator {
    return decorate(this)
//  return Decorator::class.java.constructors.first().newInstance(this).javaClass as Decorator
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
            //beverage = make_unique<Lemon>(move(beverage))
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
    //dialogWithUser()
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
            Lemon(
                Cinnamon(Latte()), 2U,
            ), 2U, IceCubeType.Dry
        ),
        2U
    )

    // Выписываем счет покупателю
    println("${beverage2.getDescription()} , cost: ${beverage2.getCost()}")

    // Подробнее рассмотрим работу MakeCondiment и оператора <<
    val lemon2 = makeCondiment<Lemon>(args = arrayOf(2U))
    // iceCubes - функция, добавляющая "3 кусочка льда" к любому напитку
    val iceCubes3 =
        makeCondiment<IceCubes>(args = arrayOf(3, IceCubeType.Water))

    val tea = Tea()
    // декорируем чай двумя дольками лимона и тремя кусочками льда
    val lemonIceTea = iceCubes3(lemon2(tea))

    val oneMoreLemonIceTea = Tea() +
            makeCondiment<Lemon>(arrayOf(2, IceCubeType.Dry)) +
            makeCondiment<IceCubes>(arrayOf(3, IceCubeType.Water))

    val beverage3 = Latte() +
            makeCondiment<Cinnamon>() +
            makeCondiment<Lemon>() +
            makeCondiment<IceCubes>(arrayOf(2, IceCubeType.Dry)) +
            makeCondiment<ChocolateCrumbs>(2)            // посыпаем шоколадной крошкой

    println("${beverage3.getDescription()} , cost: ${beverage3.getCost()}")

    val beverage4 = Milkshake() +
            makeCondiment<CSyrup>(CSyrup::class, SyrupType.Maple) +
            makeCondiment<CCoconutFlakes>(CCoconutFlakes::class, 8)

    // Выписываем счет покупателю
    println("${beverage4.getDescription()} , cost: ${beverage4.getCost()}")
}