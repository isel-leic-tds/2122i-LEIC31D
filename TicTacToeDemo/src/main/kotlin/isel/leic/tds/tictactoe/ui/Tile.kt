package isel.leic.tds.tictactoe.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import isel.leic.tds.tictactoe.model.Player

/**
 * Composable used to display a Tic-Tac-Toe board tile.
 *
 * @param move          the player that made a move on that tile, or null if no move has been done yet
 * @param onSelected    the function called when the tile is selected
 */
@Composable
fun Tile(move: Player?, onSelected: (Player?) -> Unit = { }) {
    Box(modifier = Modifier
        .size(192.dp)
        .background(MaterialTheme.colors.background)
        .clickable(true) {
            onSelected(move)
        }
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