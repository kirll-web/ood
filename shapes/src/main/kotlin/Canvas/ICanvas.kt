package Canvas

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer

interface ICanvas {
    fun setColor(c: String)// . Изменяет текущий цвет рисования.
    fun moveTo(
        x: Double,
        y: Double
    )//. Переставляет перо в точку с заданными координатами. Точка (x, y) становится текущей позицией рисования.

    fun lineTo(
        x: Double,
        y: Double
    ) //. Соединяет текущую позицию рисования отрезком прямой с точкой (x, y). Точка (x, y) становится текущей позицией рисования. Линия рисуется текущим цветом линии. Линия рисуется текущим цветом.

    fun drawEllipse(
        cx: Double,
        cy: Double,
        rx: Double,
        ry: Double
    )//. Рисует эллипс с центром в точке (cx, cy), rx - горизонтальный радиус, ry - вертикальный радиус. Эллипс рисуется текущим цветом. Текущая позиция рисования не меняется.

    fun drawText(
        left: Double, top: Double, fontSize: Double, text: String
    )

    // этот метод должен быть только в конкретном канвасе, в интерфейсе его быть не должно
    fun draw(drawScope: DrawScope, rememberText: TextMeasurer)
}