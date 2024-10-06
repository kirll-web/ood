package Canvas

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import common.Point
import common.getOffset

class Canvas : ICanvas {
    private var mColor = Color(Color.DEFAULT_COLOR)
    private var mCursor = Point(0.0, 0.0)
    private var mShapes: List<IPrimitive> = listOf()
    val shapes
        get() = mShapes

    override fun setColor(c: String) {
        mColor.changeColor(c)
    }

    override fun moveTo(x: Double, y: Double) {
        mCursor = Point(x, y)
    }

    // Переставляет перо в точку с заданными координатами. Точка (x, y)
    // становится текущей позицией рисования.
    override fun lineTo(x: Double, y: Double) {
        mShapes = mShapes.plus(Line(
            start = getOffset(mCursor.x, mCursor.y),
            end = getOffset(x, y),
            color = mColor
        ))
        moveTo(x, y)
    }

    override fun draw(drawScope: DrawScope, rememberText: TextMeasurer) {
        mShapes.forEach {
            it.draw(drawScope, rememberText)
        }
    }
    /*
    * Соединяет текущую позицию рисования отрезком прямой с точкой (x, y).
    * Точка (x, y) становится текущей позицией рисования.
    * Линия рисуется текущим цветом линии. Линия рисуется текущим цветом.
    * */
    override fun drawEllipse(cx: Double, cy: Double, rx: Double, ry: Double) {
        mShapes = mShapes.plus(Ellipse(
            topLeft = getOffset(cx, cy),
            rx = rx,
            ry = ry,
            color = mColor
        ))
    }

    // TODO: ПРИГОДИТСЯ ДЛЯ ОТРИСОВКИ КРУГА
    // TODO: topLeft = getOffset(cx - rx/2, cy - ry/2),
    /*
    * Рисует эллипс с центром в точке (cx, cy),
    *  rx - горизонтальный радиус, ry - вертикальный радиус.
    *  Эллипс рисуется текущим цветом. Текущая позиция рисования не меняется.*/
    override fun drawText(
        left: Double,
        top: Double,
        fontSize: Double,
        text: String
    ) {
        mShapes = mShapes.plus(Text(
            start = getOffset(left, top),
            fontSize = fontSize,
            text = text,
            color = mColor
        ))
    }
}