package isel.leic.tds.tictactoe.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import isel.leic.tds.tictactoe.model.Player


/**
 * Composable used to represent a Tile in the Tic-Tac-Toe game board
 */
@Composable
fun Tile(move: Player?, onSelected: (Player?) -> Unit = { }) {
    Box(modifier = Modifier
        .size(192.dp)
        .clickable { onSelected(move) }
        .padding(32.dp)
    ) {
        move?.let {
            val image = painterResource(
                if (it == Player.CIRCLE) "circle.png"
                else "cross.png"
            )
            Image(painter = image, contentDescription = "Move")
        }
    }
}

@Composable
@Preview
fun CircleMove() {
    Tile(Player.CIRCLE)
}