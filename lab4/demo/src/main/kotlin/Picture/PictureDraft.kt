package Picture

import Canvas.ICanvas
import shape.Shape

class PictureDraft: IPictureDraft {
    private var mShapes: List<Shape> = listOf()

    fun addShape(shape: Shape) {
        mShapes = mShapes.plus(shape)
    }

    fun drawPicture(canvas: ICanvas) = mShapes.forEach {
        it.draw(canvas)
    }

    override fun getShapeCount() = mShapes.count()

    override fun getShape(index: Int) = mShapes[index]
}