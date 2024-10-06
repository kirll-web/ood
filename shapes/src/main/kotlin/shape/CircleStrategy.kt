package shape

import Canvas.ICanvas
import common.Point

class CircleStrategy(
    start: Point,
    radius: Double,
) : IShapeTypeStrategy {

    val start
        get() = mStart
    val radius
        get() = mRadius

    private var mStart = start
    private var mRadius = radius

    override fun drawShape(canvas: ICanvas, color: ShapeColor) {
        canvas.setColor(color.value)
        canvas.drawEllipse(
            mStart.x,
            mStart.y,
            mRadius,
            mRadius
        )
    }

    override fun moveShape(dx: Double, dy: Double) {
        mStart = Point(x = mStart.x + dx, y = mStart.y + dx)
    }


    override fun getInfo() = "${start.x} ${start.y} $radius"
}