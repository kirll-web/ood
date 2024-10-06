package shape

import Canvas.ICanvas


class Shape(
    val id: String,
    color: ShapeColor,
    strategy: IShapeTypeStrategy
) {
    val color
        get() = mColor
    private var mColor = color
    private var mStrategy = strategy

    fun changeColor(color: ShapeColor) {
        mColor.changeColor(color)
    }

    fun drawShape(canvas: ICanvas) = mStrategy.drawShape(canvas, color)

    fun moveShape(dx: Double, dy: Double) = mStrategy.moveShape(dx = dx, dy = dy)

    fun getInfo() = "$id ${color.value} ${mStrategy.getInfo()}"

    fun changeStrategy(strategy: IShapeTypeStrategy) {
        mStrategy = strategy
    }

    fun copy() = Shape(
        id = id,
        color = ShapeColor(color.value),
        strategy = mStrategy
    )
}