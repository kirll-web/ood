package Designer

import Picture.PictureDraft
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class Designer(
    val shapeFacroty: IShapeFactory
): IDesigner {
    override fun createDraft(): PictureDraft {
        val draft = PictureDraft()

        val scope = CoroutineScope(Job() + Dispatchers.IO)

        scope.launch {
            while (true) {
                try {
                    val line = readLine()
                    draft.addShape(shapeFacroty.createShape(line))
                } catch(ex: Exception) {
                    println(ex.message)
                }
            }
        }
    }
}