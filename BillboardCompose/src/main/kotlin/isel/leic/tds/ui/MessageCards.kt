package isel.leic.tds.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import isel.leic.tds.billboard.Author
import isel.leic.tds.billboard.Message

/**
 * Displays the given billboard message
 * @param message   The message to be displayed
 */
@Composable
fun MessageCard(message: Message) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .border(width = Dp.Hairline, color = MaterialTheme.colors.primarySurface, shape = MaterialTheme.shapes.small)
    ) {
        Text(text = message.content, modifier = Modifier.padding(24.dp))
    }
}

/**
 * Displays the author information.
 * @param author    The author to be displayed
 * @param onSelected   The function called when the author is selected in the UI
 */
@Composable
fun AuthorCard(author: Author, onSelected: (Author) -> Unit = { }) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(onClick = { onSelected(author) }),
        elevation = 4.dp
    ) {
        val color = MaterialTheme.colors.primaryVariant
        Row(modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                tint = color,
                contentDescription = "user icon",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = author.id, fontSize = 16.sp, color = color, modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}