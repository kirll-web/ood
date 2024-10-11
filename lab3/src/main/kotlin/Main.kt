package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
Функциональный объект, создающий лимонную добавку
*/
fun makeLemon(quantity: UInt) = {  beverage: IBeverage ->
    Lemon(beverage, quantity)
}


/*
Функция, возвращающая функцию, создающую коричную добавку
*/
fun makeCinnamon(): (b: IBeverage) -> Cinnamon = { b ->
    Cinnamon(b)
}

inline  fun <reified T> makeCondiment(vararg params: Any?): (IBeverage) -> CondimentDecorator = { base ->
        when(T::class.java) {
            Lemon::class.java -> Lemon(base, params[0] as UInt)
            IceCubes::class.java -> IceCubes(base, params[0] as UInt)
            Syrup::class.java -> Syrup(base, params[0] as SyrupType)
            CoconutFlakes::class.java -> CoconutFlakes(base, params[0] as UInt)
            ChocolateCrumbs::class.java -> ChocolateCrumbs(base, params[0] as UInt)
            Cinnamon::class.java-> Cinnamon(base)
            else -> throw IllegalArgumentException("Такого класса не существует")
        }
}

/*
Перегруженная версия оператора <<, которая предоставляет нам синтаксический сахар
для декорирования компонента

Позволяет создать цепочку оборачивающих напиток декораторов следующим образом:
auto beverage = make_unique<CConcreteBeverage>(a, b, c)
					<< MakeCondimentA(d, e, f)
					<< MakeCondimentB(g, h)

Функциональные объекты MakeCondiment* запоминают аргументы, необходимые для создания
дополнения, и возвращают фабричную функцию, принимающую оборачиваемый напиток, которая
при своем вызове создаст нужный объект Condiment, передав ему запомненные аргументы.
Использование:
	auto beverage =
		make_unique<CConcreteBeverage>(a, b, c)
		<< MakeCondimentA(d, e, f)
		<< MakeCondimentB(g, h)
или даже так:
	auto beverage =
		make_unique<CConcreteBeverage>
		<< MakeCondiment<CondimentA>(d, e, f)
		<< MakeCondiment<CondimentB>(g, h)
В последнем случае нет необходимости писать вручную реализации MakeCondimentA и MakeCondimentB, т.к.
необходимую реализацию сгенерирует компилятор

Классический способ оборачивания выглядел бы так:
	auto baseBeverage = make_unique<CConcretedBeverage>(a, b, c)
	auto wrappedWithCondimentA = make_unique<CCondimentA>(move(baseBeverage), d, e, f)
	auto beverage = make_unique<CCondimentB>(move(wrappedWithCondimentA), g, h)
либо так:
	auto beverage = make_unique<CCondimentB>(
						make_unique<CCondimentA>(
							make_unique<CConcreteBeverage>(a, b, c), // Напиток
							d, e, f	// доп. параметы CondimentA
						),
						g, h		// доп. параметры CondimentB
					)

unique_ptr<Lemon> operator << (IBeveragePtr && lhs, const MakeLemon & factory)
{
	return factory(move(lhs))
}
unique_ptr<CCinnamon> operator << (IBeveragePtr && lhs, const MakeCinnamon & factory)
{
	return factory(move(lhs))
}
*/
/*template <typename Component, typename Decorator>
auto operator << (Component && component, const Decorator & decorate)
{
    return decorate(forward<Component>(component))
}*/

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
    val lemon2 = makeCondiment<Lemon>( 2U)
    // iceCubes - функция, добавляющая "3 кусочка льда" к любому напитку
    val iceCubes3 = makeCondiment<IceCubes>( 3U, IceCubeType.Water)

    val tea = Tea()

    // декорируем чай двумя дольками лимона и тремя кусочками льда
    val lemonIceTea = iceCubes3(lemon2(tea))

    val oneMoreLemonIceTea = Tea() +
            makeCondiment<Lemon>( 2U) +
            makeCondiment<IceCubes>(3U, IceCubeType.Water)

    val beverage3 = Latte() + makeCondiment<Cinnamon>() + makeCondiment<Lemon>( 2U) +
            makeCondiment<IceCubes>( 2U, IceCubeType.Dry) +
            makeCondiment<ChocolateCrumbs>( 2U)            // посыпаем шоколадной крошкой

    println("${beverage3.getDescription()} , cost: ${beverage3.getCost()}")

    val beverage4 = Milkshake() +
            makeCondiment<Syrup>(SyrupType.Maple) +
            makeCondiment<CoconutFlakes>(8U)

    // Выписываем счет покупателю
    println("${beverage4.getDescription()} , cost: ${beverage4.getCost()}")
}