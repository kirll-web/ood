package shape

import Canvas.ICanvas
import common.Point
import Color.ShapeColor
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI


class RegularRectangle(
    private val center: Point,
    private val vertexCount: UInt,
    private val radius: Double,
    color: ShapeColor
) : Shape(color) {

    override fun draw(canvas: ICanvas) {
        super.draw(canvas)
        // Проверка входных данных
        require(vertexCount > 2U) { "Число сторон должно быть больше двух и нечетным." }

        val x = center.x
        val y = center.y

        for (i in 0 until vertexCount.toInt() - 1) {
            val p1 = Point(
                x + radius * cos((2 * PI * (i + 1) / vertexCount.toInt())),
                y + radius * sin((2 * PI * (i + 1) / vertexCount.toInt()))
            )
            val p2 = Point(
                x + radius * cos((2 * PI * (i + 1) / vertexCount.toInt())),
                y + radius * sin((2 * PI * (i + 1) / vertexCount.toInt()))
            )
            canvas.drawLine(p1, p2)
        }
    }

    fun getCenter() = center
    fun getVertexCount() = vertexCount
    fun getRadius() = radius
}