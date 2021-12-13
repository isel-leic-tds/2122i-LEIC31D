// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

enum class TileColor {
    BLACK, WHITE
}

@Composable
fun Tile(tileColor: TileColor, hasPiece: Boolean = false) {
    val colorRGB =
        if (tileColor == TileColor.BLACK) Color(0xFFA57551)
        else Color(0xFFEBD1A6)

    Box(Modifier.background(colorRGB).size(100.dp)) {
        if (hasPiece) {
            Image(
                painter = painterResource("black-king.png"),
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        Row {
            for (count in 0..3) {
                val tileColor =
                    if(count % 2 == 0) TileColor.WHITE
                    else TileColor.BLACK
                Tile(tileColor)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
