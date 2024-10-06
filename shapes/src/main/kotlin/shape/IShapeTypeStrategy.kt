package shape

import Canvas.ICanvas

interface IShapeTypeStrategy {
    fun drawShape(canvas: ICanvas, color: ShapeColor)
    fun moveShape(dx: Double, dy: Double)
    fun getInfo(): String
}