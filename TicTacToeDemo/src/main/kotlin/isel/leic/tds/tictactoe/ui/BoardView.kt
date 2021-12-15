package isel.leic.tds.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import isel.leic.tds.tictactoe.model.*

/**
 * Composable used to display a Tic-Tac-Toe board.
 *
 * @param board          the board
 * @param onTileSelected the function called when a tile is selected
 */
@Composable
fun BoardView(board: Board, onTileSelected: (Player?, coordinate: Coordinate) -> Unit) {
    val lineThickness = 8.dp
    Column(modifier = Modifier.background(Color.Black)) {
        repeat(board.side) { rowIndex ->
            Row {
                repeat(board.side) { count ->
                    val tile = board[rowIndex.Row, count.Column]
                    Tile(tile, onSelected = {
                        onTileSelected(it, Coordinate(rowIndex.Row, count.Column))
                    })
                    if (count < board.side - 1)
                        Spacer(modifier = Modifier.width(lineThickness))
                }
            }
            if (rowIndex < board.side - 1)
                Spacer(modifier = Modifier.height(lineThickness))
        }
    }
}