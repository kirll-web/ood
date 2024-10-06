package shape

class ShapeColor(color: String) {
    val value
        get() = mColor
    private var mColor: String = color

    fun changeColor(color: ShapeColor) {
        mColor = color.value
    }

    companion object {
        const val DEFAULT_COLOR = "#000000"
    }
}