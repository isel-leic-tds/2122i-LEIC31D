package isel.leic.tds.tictactoe.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogState
import isel.leic.tds.tictactoe.domain.GameId
import isel.leic.tds.tictactoe.domain.toGameIdOrNull

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GetGameId(onGameIdEntered: (GameId) -> Unit, onCancel: () -> Unit) = Dialog(
    onCloseRequest = onCancel,
    title = "Game Identifier",
    resizable = false,
    state = DialogState(size = DpSize.Unspecified)
) {

    val userInput = remember { mutableStateOf("") }
    val currentUserInput = userInput.value

    val maybeOnGameIdEntered = {
        val gameId = currentUserInput.toGameIdOrNull()
        if (gameId == null) { userInput.value = "" }
        else { onGameIdEntered(gameId) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Please enter the game id:", modifier = Modifier.padding(8.dp))
        Row {
            TextField(
                modifier = Modifier
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                            maybeOnGameIdEntered()
                        }
                        true
                    },
                value = currentUserInput,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFEFEFEF)),
                onValueChange = { userInput.value = it }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = maybeOnGameIdEntered) {
                Text(text = "OK")
            }
        }
    }

}