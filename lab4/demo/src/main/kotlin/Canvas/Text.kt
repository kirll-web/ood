package Canvas

import Color.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp

class Text(
    private val start: Offset,
    private val color: Color,
    private val text: String,
    private val fontSize: Double
): IPrimitive {
    override fun draw(drawScope: DrawScope, rememberText: TextMeasurer) {
        drawScope.drawText(
            rememberText,
            text,
            start,
            TextStyle(
                fontSize = fontSize.sp,
                color = color.parseToComposeColor()
            )
        )
    }
}