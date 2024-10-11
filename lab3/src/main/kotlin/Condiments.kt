package org.example

// Базовый декоратор "Добавка к напитку". Также является напитком
abstract class CondimentDecorator(private val beverage: IBeverage) : IBeverage {
    override fun getDescription() = "${beverage.getDescription()}, ${getCondimentDescription()}"

    override fun getCost() = beverage.getCost() + getCondimentCost()

    // Стоимость и описание добавки вычисляется в классах конкретных декораторов
    abstract fun getCondimentDescription(): String
    abstract fun getCondimentCost(): Double
}

// Добавка из корицы
class Cinnamon(beverage: IBeverage) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 20.0

    override fun getCondimentDescription() = "Cinnamon"
}

// Лимонная добавка
class Lemon(
    beverage: IBeverage,
    private val quantity: UInt
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 10.0 * quantity.toDouble()
    override fun getCondimentDescription() = "Lemon x$quantity"
}

enum class Condiments {
    ICE_CUBES,
    SYRUP,
    LEMON,
    CINNAMON,
    CHOCOLATE_CRUMBS,
    COCONUT_FLAKES
}


enum class IceCubeType {
    Dry,    // Сухой лед (для суровых сибирских мужиков)
    Water    // Обычные кубики из воды
}

// Добавка "Кубики льда". Определяется типом и количеством кубиков, что влияет на стоимость
// и описание
class IceCubes(
    beverage: IBeverage,
    private val quantity: UInt,
    private val type: IceCubeType = IceCubeType.Water
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = when (type) {
        IceCubeType.Dry -> 10.0
        IceCubeType.Water -> 5.0
    }.also { it * quantity.toDouble() }

    override fun getCondimentDescription() = when (type) {
        IceCubeType.Dry -> "Dry"
        IceCubeType.Water -> "Water"
    }.also { "$it ice cubes x$quantity" }
}

// Тип сиропа
enum class SyrupType {
    Chocolate,    // Шоколадный
    Maple,        // Кленовый
}

// Добавка "Сироп"
class Syrup(
    beverage: IBeverage,
    private val syrup: SyrupType
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 15.0

    override fun getCondimentDescription() = when (syrup) {
        SyrupType.Chocolate -> "Chocolate"
        SyrupType.Maple -> "Maple"
    }.also { "$syrup syrup" }
}

// Шоколадная крошка
class ChocolateCrumbs(
    beverage: IBeverage,
    private val mass: UInt,
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 2.0 * mass.toDouble()

    override fun getCondimentDescription() = "Chocolate crumbs $mass g"
}

// Кокосовая стружка
class CoconutFlakes(
    beverage: IBeverage,
    private val mass: UInt,
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 1.0 * mass.toDouble()

    override fun getCondimentDescription() = "Coconut flakes $mass g"
}
