import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        println("Composing App")
        Outer()
    }
}

@Composable
fun Outer() {
    println("Composing Outer")

    val counter = remember { mutableStateOf(0) }
    val currentCounter = counter.value

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
        Text("Counter = $currentCounter", fontSize = 24.sp)
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            counter.value = currentCounter + 1
        }) {
            Text("Inc")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Inner("Start $currentCounter")
    }
}

@Composable
fun Inner(text: String) {
    println("Composing Inner with $text")

    LaunchedEffect(text) {
        println("Starting coroutine with $text")
        while (true) {
            delay(1_000)
            print(text)
        }
    }

    Button(onClick = {}) {
        Text(text)
    }
}


fun main() = application {
    Window(
        title = "Demo",
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(width = 400.dp, height = Dp.Unspecified))
    ) {
        App()
    }
}
