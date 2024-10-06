package ui

import Picture.Picture
import common.*
import shape.*
import Canvas.ICanvas

class Controller(
    private val canvas: ICanvas,
    private val picture: Picture
) {
    fun getCommand(command: String) = parseCommand(command)
    private fun parseCommand(command: String) {
        val args = command.trim().mySplit()
        val headCommand = parseCommandHead(args[0])
        when (headCommand) {
            Commands.ADD_SHAPE -> makeShapeAndApplyAction(
                args,
                picture,
                addShapeAction = { shape ->
                    picture.addShape(shape)
                }
            )

            Commands.MOVE_SHAPE -> moveShape(args)
            Commands.DRAW_PICTURE -> {picture.drawPicture(canvas)}

            Commands.CHANGE_SHAPE -> makeShapeAndApplyAction(
                args,
                picture,
                changeShapeAction = { id, strategy ->
                    picture.changeShape(id, strategy)
                }
            )

            Commands.CHANGE_COLOR -> {
                if (!args.isEnoughArgs(3)) return
                val id = args[1]
                val colorString = args[2]

                if (!colorString.isHexColorCode()) {
                    println("It's not hex code for color")
                    return
                }

                picture.changeColor(id, ShapeColor(colorString))
            }

            Commands.DRAW_SHAPE -> {
                if (!args.isEnoughArgs(2)) return
                val id = args[1]

                picture.drawShape(id, canvas)
            }

            Commands.LIST -> picture.list()
            Commands.MOVE_PICTURE -> {
                if (!args.isEnoughArgs(3)) return
                val dx = args[1]
                val dy = args[2]


                if (!(dx.isDouble("dx")
                            && dy.isDouble("dy"))
                ) return

                picture.movePicture(dx.toDouble(), dy.toDouble())
            }

            Commands.DELETE_SHAPE -> {
                if (!args.isEnoughArgs(2)) return
                val id = args[1]

                picture.deleteShape(id)
            }

            Commands.UNKNOWN -> {
                println("unknown command")
            }
        }
    }

    enum class Commands {
        ADD_SHAPE,
        MOVE_SHAPE,
        DRAW_PICTURE,
        CHANGE_SHAPE,
        CHANGE_COLOR,
        DRAW_SHAPE,
        DELETE_SHAPE,
        LIST,
        MOVE_PICTURE,
        UNKNOWN
    }

    private fun parseCommandHead(head: String) = when (head) {
        "AddShape" -> Commands.ADD_SHAPE
        "DrawPicture" -> Commands.DRAW_PICTURE
        "DrawShape" -> Commands.DRAW_SHAPE
        "ChangeShape" -> Commands.CHANGE_SHAPE
        "ChangeColor" -> Commands.CHANGE_COLOR
        "DeleteShape" -> Commands.DELETE_SHAPE
        "List" -> Commands.LIST
        "MovePicture" -> Commands.MOVE_PICTURE
        "MoveShape" -> Commands.MOVE_SHAPE
        "CloneShape" -> Commands.MOVE_SHAPE
        else -> Commands.UNKNOWN
    }

    private fun parseViewShape(viewShape: String): ViewShape = when (viewShape) {
        "rectangle" -> ViewShape.RECTANGLE
        "circle" -> ViewShape.CIRCLE
        "text" -> ViewShape.TEXT
        "line" -> ViewShape.LINE
        "triangle" -> ViewShape.TRIANGLE
        else -> ViewShape.UNKNOWN
    }

    enum class ViewShape {
        RECTANGLE,
        CIRCLE,
        TEXT,
        LINE,
        TRIANGLE,
        CLONE_SHAPE,
        UNKNOWN
    }

    private fun makeShapeAndApplyAction(
        args: List<String>,
        picture: Picture,
        addShapeAction: (Picture.(shape: Shape) -> Unit)? = null,
        changeShapeAction: (Picture.(id: String, strategy: IShapeTypeStrategy) -> Unit)? = null
    ) {
        if (!args.isEnoughArgs(4)) return

        val id = args[1]
        val color = args[2]
        val shapeType = parseViewShape(args[3])

        if (!color.isHexColorCode()) {
            println("It's not hex code for color")
            return
        }

        getStrategy(shapeType, args)?.let { strategy ->
            addShapeAction?.let { change ->
                getStrategy(shapeType, args)?.let { strategy ->
                    picture.change(
                        Shape(
                            id = id,
                            color = ShapeColor(color),
                            strategy
                        )
                    )
                }
            }

            changeShapeAction?.let { change ->
                getStrategy(shapeType, args)?.let {
                    strategy
                    picture.change(id, strategy)
                }
            }
        }
    }

    private fun getStrategy(shapeType: ViewShape, args: List<String>) = when (shapeType) {
        ViewShape.RECTANGLE -> getRectangleStrategy(args)

        ViewShape.CIRCLE -> getCircleStrategy(args)

        ViewShape.TEXT -> getTextStrategy(args)

        ViewShape.LINE -> getLineStrategy(args)

        ViewShape.TRIANGLE -> getTriangleStrategy(args)

        else -> {
            println("unknown shape")
            null
        }
    }

    private fun getRectangleStrategy(
        args: List<String>
    ): RectangleStrategy? {
        if (!args.isEnoughArgs(8)) return null
        //AddShape sh1 #ff0000 rectangle <left> <top> <width> <height>
        val left = args[4]
        val top = args[5]
        val width = args[6]
        val height = args[7]

        if (!(left.isDouble("left")
                    && top.isDouble("top")
                    && width.isDouble("width")
                    && height.isDouble("height"))
        ) return null


        return RectangleStrategy(
            start = (left to top).convertDoubleToPoint(),
            width = width.toDouble(),
            height = height.toDouble()
        )
    }

    private fun getCircleStrategy(
        args: List<String>
    ): CircleStrategy? {
        if (!args.isEnoughArgs(7)) return null
        val x = args[4]
        val y = args[5]
        val radius = args[6]

        if (!(x.isDouble("x")
                    && y.isDouble("y")
                    && radius.isDouble("radius"))
        ) return null

        return CircleStrategy(
            start = (x to y).convertDoubleToPoint(),
            radius = radius.toDouble()
        )
    }

    private fun getTriangleStrategy(
        args: List<String>
    ): TriangleStrategy? {
        if (!args.isEnoughArgs(10)) return null
        //AddShape sh1 #ff0000 triangle <x1> <y1> <x2> <y2> <x3> <y3>
        val x1 = args[4]
        val y1 = args[5]
        val x2 = args[6]
        val y2 = args[7]
        val x3 = args[8]
        val y3 = args[9]

        if (!(x1.isDouble("x1")
                    && y1.isDouble("y1")
                    && x2.isDouble("x2")
                    && y2.isDouble("y2")
                    && x3.isDouble("x3")
                    && y3.isDouble("y3"))
        ) return null

        return TriangleStrategy(
            p1 = (x1 to y1).convertDoubleToPoint(),
            p2 = (x2 to y2).convertDoubleToPoint(),
            p3 = (x3 to y3).convertDoubleToPoint()
        )
    }


    private fun getLineStrategy(
        args: List<String>
    ): LineStrategy? {
        if (!args.isEnoughArgs(8)) return null
        //AddShape sh1 #ff0000 line <x1> <y1> <x2> <y2>
        val x1 = args[4]
        val y1 = args[5]
        val x2 = args[6]
        val y2 = args[7]

        if (!(x1.isDouble("x1")
                    && y1.isDouble("y1")
                    && x2.isDouble("x2")
                    && y2.isDouble("y2"))
        ) return null

        return LineStrategy(
            start = (x1 to y1).convertDoubleToPoint(),
            end = (x2 to y2).convertDoubleToPoint(),
        )
    }

    private fun getTextStrategy(
        args: List<String>
    ): TextStrategy? {
        if (!args.isEnoughArgs(8)) return null
        //AddShape sh1 #ff0000 text <left> <top> <размер> <тест>
        val left = args[4]
        val top = args[5]
        val textSize = args[6]
        val text = args[7]

        if (!(left.isDouble("left")
                    && top.isDouble("top")
                    && textSize.isDouble("textSize"))
        ) return null

        return TextStrategy(
            start = (left to top).convertDoubleToPoint(),
            textSize = textSize.toDouble(),
            text = text
        )
    }

    private fun moveShape(args: List<String>) {
        if (!args.isEnoughArgs(4)) return
        val id = args[1]
        val dx = args[2]
        val dy = args[3]


        if (!(dx.isDouble("dx")
                    && dy.isDouble("dy"))
        ) return

        picture.moveShape(id, dx.toDouble(), dy.toDouble())
    }

}

/** Commands:
 * AddShape sh1 #ff0000 rectangle 10 20 30 40
 * Фигуры:
 *      AddShape sh1 #ff0000 text <left> <top> <размер> <тест>
 *      AddShape sh1 #ff0000 line <x1> <y1> <x2> <y2>
 *      AddShape sh1 #ff0000 triangle <x1> <y1> <x2> <y2> <x3> <y3>
 *      AddShape sh1 #ff0000 rectangle <left> <top> <width> <height>
 *      AddShape sh1 #ff0000 circle <x> <y> <r>

 * DrawPicture
 * DrawShape <id>
 * ChangeShape sh1 circle 100 110 15
 * ChangeColor <id> <цвет>
 * List
 * DeleteShape <id>
 * MovePicture <dx> <dy>
 * MoveShape <id> <dx> <dy>
 */

       
