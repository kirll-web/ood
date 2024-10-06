package shape

import Canvas.ICanvas
import common.Point

class LineStrategy(
    start: Point,
    end: Point,
) : IShapeTypeStrategy {

    val start
        get() = mStart
    val end
        get() = mEnd

    private var mStart = start
    private var mEnd = end

    override fun drawShape(canvas: ICanvas, color: ShapeColor) {
        canvas.setColor(color.value)
        canvas.moveTo(
            start.x, start.y
        )

        canvas.lineTo(end.x, end.y)
    }

    override fun moveShape(dx: Double, dy: Double) {
        mStart = Point(x = mStart.x + dx, y = mStart.y + dx)
        mEnd = Point(x = mStart.x + dx, y = mStart.y + dx)
    }


    override fun getInfo() = "${start.x} ${start.y}  ${end.x} ${end.y}"
}