package Canvas

import androidx.compose.ui.graphics.Color as ComposeColor
import common.fromHex
import common.isHexColorCode

class Color(color: String) {
    val value
        get() = mValue

    private var mValue = color

    fun changeColor(color: String) {
        if(!color.isHexColorCode()) {
            println("This is no hex code for color")
            return
        }

        mValue = color
    }

    fun parseToComposeColor() = ComposeColor.fromHex(mValue)

    companion object {
        const val DEFAULT_COLOR = "#000000"
    }
}