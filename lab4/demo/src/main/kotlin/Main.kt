import Canvas.Canvas
import Picture.PictureDraft
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ui.Controller

@Composable
@Preview
fun App() {
    val canvas = Canvas()
    var pictureDraft by remember {
        mutableStateOf(
            PictureDraft()
        )
    }
    var drawerState by remember {
        mutableStateOf(
            canvas.shapes
        )
    }

    val rememberText = rememberTextMeasurer()

    val controller = Controller(canvas, pictureDraft)



    MaterialTheme {
        drawerState

        Canvas(Modifier.fillMaxSize()) {
            canvas.draw(this, rememberText)
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
