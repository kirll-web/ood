package Color

import androidx.compose.ui.graphics.Color as ComposeColor
import common.fromHex

enum class ShapeColor(private val value: String) {
    GREEN(""),
    RED(""),
    BLUE(""),
    YELLOW(""),
    PINK(""),
    BLACK("#000000");

    fun parseToComposeColor() = ComposeColor.fromHex(value)
}
