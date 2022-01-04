package isel.leic.tds.tictactoe.ui.board

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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
fun TileImageView(move: Player?, onSelected: (Player?) -> Unit = { }) = Box(
    modifier = Modifier
        .size(192.dp)
        .background(MaterialTheme.colors.background)
        .clickable(true) {
            onSelected(move)
        }
        .padding(32.dp)
) {
    move?.let {
        val imageResource = if (it == Player.CIRCLE) "circle_2.png" else "cross_2.png"
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Tile image"
        )
    }
}


/**
 * Composable used to display a Tic-Tac-Toe board tile. It's an alternative to [TileImageView], that uses drawing in a
 * canvas instead of an image.
 *
 * @param move          the player that made a move on that tile, or null if no move has been done yet
 * @param onSelected    the function called when the tile is selected
 */
@Composable
fun TileView(move: Player?, onSelected: (Player?) -> Unit = { }) {
    Canvas(modifier = Modifier
        .size(132.dp)
        .background(MaterialTheme.colors.background)
        .clickable(enabled = true, onClick = { onSelected(move) })
        .padding(24.dp)
    ) {
        move?.let {
            val strokeWidth = 12F
            val color = Color.Red
            if (it == Player.CIRCLE) {
                drawCircle(color = color, center = center, radius = size.width/2, style = Stroke(width = strokeWidth))
            }
            else {
                fun drawCrossLine(start: Offset, end: Offset) =
                    drawLine(color = color, strokeWidth = strokeWidth, cap = StrokeCap.Round, start = start, end = end)

                drawCrossLine(
                    start = Offset.Zero,
                    end = Offset(x = size.width, y = size.height)
                )
                drawCrossLine(
                    start = Offset(x = size.width, y = 0F),
                    end = Offset(x = 0F, y = size.height)
                )
            }
        }
    }
}

@Composable
@Preview
fun CircleViewPreview() {
    TileView(Player.CIRCLE)
}

@Composable
@Preview
fun CrossViewPreview() {
    TileView(Player.CROSS)
}