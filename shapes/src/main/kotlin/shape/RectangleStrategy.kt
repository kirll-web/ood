package shape

import Canvas.ICanvas
import common.Point

class RectangleStrategy(
    start: Point,
    width: Double,
    height: Double
) : IShapeTypeStrategy {

    val start
        get() = mStart
    val width
        get() = mWidth
    val height
        get() = mHeight

    private var mStart = start
    private var mWidth = width
    private var mHeight = height

    override fun drawShape(canvas: ICanvas, color: ShapeColor) {
        canvas.setColor(color.value)
        canvas.moveTo(mStart.x, mStart.y)
        canvas.lineTo(mStart.x + mWidth, mStart.y)
        canvas.lineTo(mStart.x  + mWidth, mStart.y + mHeight)
        canvas.lineTo(mStart.x, mStart.y + mHeight)
        canvas.lineTo(mStart.x, mStart.y)
    }

    override fun moveShape(dx: Double, dy: Double) {
        mStart = Point(x = mStart.x + dx, y = mStart.y + dx)
    }

    override fun getInfo() = "${start.x} ${start.y} $width $height "
}