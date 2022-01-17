import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message
import isel.leic.tds.billboard.toAuthorOrNull
import isel.leic.tds.storage.Billboard
import isel.leic.tds.ui.AuthorsPane
import isel.leic.tds.ui.LoadingPane
import isel.leic.tds.ui.MessagesPane
import isel.leic.tds.ui.PostMessagePane
import kotlinx.coroutines.launch

/**
 * The main application window
 */
@Composable
fun MainWindow(author: Author, billboard: Billboard, onClose: () -> Unit) {

    val displayConfirmExit = remember { mutableStateOf(false) }

    return Window(
        onCloseRequest = { displayConfirmExit.value = true },
        title = "Billboard: ${author.id}",
        state = rememberWindowState(position = WindowPosition(Alignment.Center))
    ) {

        val authors = remember { mutableStateOf<List<Author>?>(null) }
        LaunchedEffect(true) {
            authors.value = billboard.getAllAuthors().toList()
        }

        val coroutineScope = rememberCoroutineScope()
        val selectedAuthorPosts = remember { mutableStateOf<Iterable<Message>?>(emptyList()) }

        if (displayConfirmExit.value)
            ConfirmExitDialog(onConfirmExit = onClose, onCancelExit = { displayConfirmExit.value = false })

        Column {
            Row(modifier = Modifier.weight(weight = 1.0f, fill = true)) {
                LoadingPane(
                    modifier = Modifier.fillMaxHeight().requiredWidth(200.dp),
                    loading = authors.value == null
                ) {
                    AuthorsPane(
                        authors = authors.value,
                        authorSelected = {
                            coroutineScope.launch {
                                selectedAuthorPosts.value = null
                                println("Triggering data fetch in coroutine executing in thread ${Thread.currentThread().name}")
                                selectedAuthorPosts.value = billboard.getAllFrom(it)
                            }
                        }
                    )
                }
                LoadingPane(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.surface),
                    loading = selectedAuthorPosts.value == null
                ) {
                    MessagesPane(selectedAuthorPosts.value)
                }
            }
            Divider()
            PostMessagePane(onPost = { msg ->
                coroutineScope.launch {
                    println("Triggering post message in coroutine executing in thread ${Thread.currentThread().name}")
                    billboard.post(Message(author, msg))
                }
            })
        }
    }
}

/**
 * Dialog used to confirm application termination
 */
@Composable
fun ConfirmExitDialog(onConfirmExit: () -> Unit, onCancelExit: () -> Unit) =
    Dialog(
        onCloseRequest = onCancelExit,
        title = "Confirm Exit",
        resizable = false,
        state = rememberDialogState(size = DpSize(Dp.Unspecified, Dp.Unspecified))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Do you really want to exit the application?")
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(onClick = onCancelExit) { Text("No") }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onConfirmExit) { Text("Yes") }
            }
        }
    }

/**
 * Dialog used to get the user's identifier
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GetAuthorId(onUserIdEntered: (Author) -> Unit, onCancel: () -> Unit) =
    Dialog(
        onCloseRequest = onCancel,
        title = "User Identifier",
        resizable = false,
        state = rememberDialogState(size = DpSize(Dp.Unspecified, Dp.Unspecified))//rememberDialogState(height = 160.dp)
    ) {

        val userId = remember { mutableStateOf("") }
        fun maybeOnUserIdEntered() {
            userId.value.toAuthorOrNull()?.let { onUserIdEntered(it) }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text( text = "Please enter your user id:", modifier = Modifier.padding(8.dp))
            Row {
                TextField(
                    modifier = Modifier
                        .onKeyEvent { event ->
                            if (event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                                maybeOnUserIdEntered()
                            }
                            true
                        },
                    singleLine = true,
                    value = userId.value,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xFFEFEFEF)),
                    onValueChange = { userId.value = it }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = ::maybeOnUserIdEntered) {
                    Text("OK")
                }
            }
        }
    }