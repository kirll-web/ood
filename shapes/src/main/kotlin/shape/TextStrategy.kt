package shape

import Canvas.ICanvas
import common.Point

class TextStrategy(
    start: Point,
    text: String,
    textSize: Double
) : IShapeTypeStrategy {

    val start
        get() = mStart
    val text
        get() = mText
    val textSize
        get() = mTextSize

    private var mStart = start
    private var mText = text
    private var mTextSize = textSize

    override fun drawShape(canvas: ICanvas, color: ShapeColor) {
        canvas.setColor(color.value)
        canvas.drawText(
            mStart.x,
            mStart.y,
            mTextSize,
            mText
        )
    }

    override fun moveShape(dx: Double, dy: Double) {
        mStart = Point(x = mStart.x + dx, y = mStart.y + dx)
    }


    override fun getInfo() = "${start.x} ${start.y} $textSize $text"
}