// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            println("${Thread.currentThread().name}: onClick")
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun otherMain() {
    println("${Thread.currentThread().name}: Starting main")
    application(exitProcessOnExit = false) {
        Window(onCloseRequest = ::exitApplication) {
            App()
        }
    }
    println("${Thread.currentThread().name}: Ending main")
}

fun main() {
    println("${Thread.currentThread().name}: Starting main")

    runBlocking {

        repeat(2) {
            launch {
                println("${Thread.currentThread().name}: Coroutine $it before myBeautifulSuspendingFunction")
                myBeautifulSuspendingFunction()
                println("${Thread.currentThread().name}: Coroutine $it after myBeautifulSuspendingFunction")
            }
        }
        println("${Thread.currentThread().name}: After repeat")

    }

    println("${Thread.currentThread().name}: Ending main")
}

suspend fun myBeautifulSuspendingFunction() {
    withContext(Dispatchers.IO) {
        println("${Thread.currentThread().name}: myBeautifulSuspendingFunction before delay")
        delay(2000)
        println("${Thread.currentThread().name}: myBeautifulSuspendingFunction after delay")
    }
}