package shape

import Canvas.ICanvas
import common.Point

class TriangleStrategy(
    p1: Point,
    p2: Point,
    p3: Point,
) : IShapeTypeStrategy {

    val p1
        get() = mP1
    val p2
        get() = mP2
    val p3
        get() = mP3

    private var mP1 = p1
    private var mP2 = p2
    private var mP3 = p3

    override fun drawShape(canvas: ICanvas, color: ShapeColor) {
        canvas.setColor(color.value)
        canvas.moveTo(mP1.x, mP1.y)
        canvas.lineTo(mP2.x, mP2.y)
        canvas.lineTo(mP3.x, mP3.y)
        canvas.lineTo(mP1.x, mP1.y)
    }

    override fun moveShape(dx: Double, dy: Double) {
        mP1 = Point(x = mP1.x + dx, y = mP1.y + dx)
        mP2 = Point(x = mP2.x + dx, y = mP2.y + dx)
        mP3 = Point(x = mP3.x + dx, y = mP3.y + dx)
    }

    override fun getInfo() = " ${p1.x} ${p1.y} ${p2.x} ${p2.y} ${p3.x} ${p3.y}"

}