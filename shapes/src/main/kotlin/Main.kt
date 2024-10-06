import Canvas.Canvas
import Picture.Picture
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
    var picture by remember {
        mutableStateOf(
            Picture()
        )
    }
    var drawerState by remember {
        mutableStateOf(
            canvas.shapes
        )
    }

    val rememberText = rememberTextMeasurer()

    val controller = Controller(canvas, picture)

    val scope = CoroutineScope(Job() + Dispatchers.IO)

    scope.launch {
        while (true) {
            controller.getCommand(readln())
            drawerState = canvas.shapes
        }
    }

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
