package Picture

import Canvas.ICanvas
import shape.ShapeColor


class PictureDraft {
    private var mShapes: List<Shape> = listOf()
    private var mIds: List<String> = listOf()

    fun addShape(shape: Shape) {
        when {
            mIds.contains(shape.id) ->
                println("This id is already exists")

            else -> {
                mShapes = mShapes.plus(shape)
                mIds = mIds.plus(shape.id)
            }
        }
    }

    fun changeShape(id: String, strategy: IShapeTypeStrategy) {
        when {
            !mIds.contains(id) ->
                println("This id is not exists")

            else -> {
                mShapes.find { it.id == id }?.changeStrategy(strategy)
            }
        }
    }

    fun drawPicture(canvas: ICanvas) = mShapes.forEach {
        it.drawShape(canvas)
    }

    fun drawShape(id: String, canvas: ICanvas) {
        if (!mIds.contains(id)) {
            println("This id is not exists")
            return
        }

        mShapes.find {it.id == id}?.drawShape(canvas)
    }

    fun moveShape(id: String, dx: Double, dy: Double) {
        if (!mIds.contains(id)) {
            println("This id is not exists")
            return
        }
        mShapes.find { it.id == id }?.moveShape(dx, dy)
    }

    fun changeColor(id: String, color: ShapeColor) {
        if (!mIds.contains(id)) {
            println("This id is not exists")
            return
        }

        mShapes.find { it.id == id }?.changeColor(color)
    }

    fun list() = mShapes.forEachIndexed { index, it ->
        println("$index ${it.id} ${it.getInfo()}")
    }

    fun movePicture(dx: Double, dy: Double) {
        mShapes.forEach {
            it.moveShape(dx, dy)
        }
    }

    fun deleteShape(id: String) {
        if (!mIds.contains(id)) {
            println("This id is not exists")
            return
        }

        mShapes.find { it.id == id }?.let { shape ->
            mShapes = mShapes.minus(shape)
            mIds = mIds.minus(shape.id)
        }
    }
}