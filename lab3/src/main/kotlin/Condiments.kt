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

enum class IceCubeType {
    DRY,    // Сухой лед (для суровых сибирских мужиков)
    WATER    // Обычные кубики из воды
}

// Добавка "Кубики льда". Определяется типом и количеством кубиков, что влияет на стоимость
// и описание
class IceCubes(
    beverage: IBeverage,
    private val quantity: UInt,
    private val type: IceCubeType = IceCubeType.WATER
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = when (type) {
        IceCubeType.DRY -> 10.0
        IceCubeType.WATER -> 5.0
    }.also { it * quantity.toDouble() }

    override fun getCondimentDescription() = when (type) {
        IceCubeType.DRY -> "DRY"
        IceCubeType.WATER -> "WATER"
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


// Кокосовая стружка
class Cream(
    beverage: IBeverage
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 25.0

    override fun getCondimentDescription() = "Cream"
}

//ликёр
class Liquor(
    beverage: IBeverage,
    private val liquorType: LiquorType
) : CondimentDecorator(beverage) {
    override fun getCondimentCost(): Double = 50.0

    override fun getCondimentDescription() = "${liquorType.getDescription()} Liquor"
}

enum class LiquorType(private val value: String) {
    CHOCOLATE("Chocolate"),
    NUTTY("Nutty");

    fun getDescription() = when (value) {
        CHOCOLATE.value -> 50.0
        NUTTY.value -> 50.0
        else -> throw IllegalArgumentException("Incorrect size")
    }
}


class ChocolateSlices(
    beverage: IBeverage,
    amountSlices: UInt
) : CondimentDecorator(beverage) {

    private var mAmountSlices: UInt = 0U

    init {
        when {
            amountSlices > MAX_AMOUNT_SLICES -> {
                println("incorrect amount slices. Max: $MAX_AMOUNT_SLICES")
                mAmountSlices = MAX_AMOUNT_SLICES
            }
            else -> mAmountSlices = amountSlices
        }
    }

    override fun getCondimentCost(): Double = 10.0 * mAmountSlices.toDouble()

    override fun getCondimentDescription() = "$ChocolateSlices x$mAmountSlices"

    companion object {
        const val MAX_AMOUNT_SLICES = 5U
    }
}


/*Сливки (25р)
Шоколад (10р за дольку, максимум: 5 долек)
Ликер (2 типа – ореховый и шоколадный). 50 рублей вне зависимости от типа
Описание добавок должно включать в себя подробности, специфичные для добавки (количество долек шоколада и тип ликера).*/
