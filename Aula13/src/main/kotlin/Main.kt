// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

var counterGlobal = 0

@Composable
@Preview
fun App() {
    DesktopMaterialTheme {
        println("Composing App")
        val counter = remember { mutableStateOf(0) }
        Row {
            Text(text = counter.value.toString(), fontSize = 36.sp)
            Button(onClick = {
                counter.value -= 1
                println("Counter is ${counter.value}")
            }) {
                println("Composing Button -")
                Text(text = "-", fontSize = 36.sp)
            }
            Button(onClick = {
                counter.value += 1
                println("Counter is ${counter.value}")
            }) {
                Text(text = "+", fontSize = 36.sp)
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
