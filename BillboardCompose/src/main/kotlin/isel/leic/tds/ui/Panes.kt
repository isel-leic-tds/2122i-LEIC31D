package isel.leic.tds.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message

/**
 * The pane that displays a list of authors
 * @param authors   The authors to be displayed, if any
 */
@Composable
fun AuthorsPane(authors: Iterable<Author>?, authorSelected: (Author) -> Unit = { }) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        authors?.forEach {
            item { AuthorCard(it) { authorSelected(it) } }
        }
    }
}

/**
 * The pane that displays a list of messages
 * @param messages  The messages to be displayed, if any
 */
@Composable
fun MessagesPane(messages: Iterable<Message>?) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        messages?.forEach {
            item { MessageCard(it) }
        }
    }
}

/**
 * The pane used to get the messages to be posted by the local user
 * @param onPost    The function called when the user wishes to post a message
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PostMessagePane(onPost: (content: String) -> Unit) {
    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth().background(MaterialTheme.colors.surface).padding(8.dp)) {
        val message = remember { mutableStateOf("") }

        fun isMessageValid() = message.value.isNotBlank()
        fun postIfValid() { if (isMessageValid()) { onPost(message.value); message.value = "" } }

        TextField(
            modifier = Modifier
                .weight(weight = 1.0f, fill = true)
                .onKeyEvent {
                    if (it.key == Key.Enter && it.type == KeyEventType.KeyUp) postIfValid()
                    true
                },
            singleLine = true,
            value = message.value,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFEFEFEF)),
            onValueChange = { message.value = it }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(enabled = isMessageValid(), modifier = Modifier.align(Alignment.Bottom), onClick = ::postIfValid) {
            Text(text = "Post")
        }
    }
}

/**
 * Decorator pane that displays either its content or a progress indicator, according to the [loading] parameter.
 * @param modifier  The modifier options to be applied to the loading pane composable
 * @param loading   A boolean value indicating whether a progress indicator (true) should be displayed or not (false)
 * @param content   The pane's content
 */
@Composable
fun LoadingPane(modifier: Modifier = Modifier, loading: Boolean = true, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = modifier) {
        if (loading) CircularProgressIndicator(Modifier.align(Alignment.Center))
        else content()
    }
}