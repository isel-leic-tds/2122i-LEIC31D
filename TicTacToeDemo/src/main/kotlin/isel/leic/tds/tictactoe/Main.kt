package isel.leic.tds.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import isel.leic.tds.tictactoe.ui.Tile

@Composable
@Preview
fun App() {
    MaterialTheme {
        Tile(null, onSelected = {
            println(it)
        })
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
