package isel.leic.tds.tictactoe.model

/**
 * The Tic-Tac-Toe's board side
 */
const val TIC_TAC_TOE_SIDE = 3

/**
 * The Tic-Tac-Toe's number of tiles.
 */
const val TIC_TAC_TOE_TILE_COUNT = TIC_TAC_TOE_SIDE * TIC_TAC_TOE_SIDE

/**
 * Checks whether [value] is a valid row index
 */
fun isValidRow(value: Int) = value in 0 until TIC_TAC_TOE_SIDE

/**
 * Represents a row index in a Tic-Tac-Toe board.
 * @param value the row index. Must be in the interval 0 <= value < [TIC_TAC_TOE_SIDE]
 */
data class Row(val value: Int) {
    init { require(isValidRow(value)) }
}

/**
 * Int extensions for expressing row indexes.
 */
fun Int.toRowOrNull() = if (isValidRow(this)) Row(this) else null
fun Int.toRow() = Row(this)
val Int.Row
    get(): Row = toRow()

/**
 * Checks whether [value] is a valid column index
 */
fun isValidColumn(value: Int) = value < TIC_TAC_TOE_SIDE

/**
 * Represents a column index in a Tic-Tac-Toe board.
 * @param value the row number. Must be in the interval 0 <= value < [TIC_TAC_TOE_SIDE]
 */
data class Column(val value: Int) {
    init { require(isValidColumn(value)) }
}

/**
 * Int extensions for expressing column indexes
 */
fun Int.toColumn() = Column(this)
fun Int.toColumnOrNull() = if (isValidColumn(this)) Column(this) else null
val Int.Column
    get(): Column = toColumn()

/**
 * Represents coordinates in the Tic-Tac-Toe board
 */
data class Coordinate(val row: Row, val column: Column) {
    constructor(value: Int) : this((value / TIC_TAC_TOE_SIDE).Row, (value % TIC_TAC_TOE_SIDE).Column)
    fun toIndex() = row.value * TIC_TAC_TOE_SIDE + column.value
}

fun isInCoordinateRange(value: Int) = value < TIC_TAC_TOE_TILE_COUNT

fun Int.toCoordinate() = Coordinate(this)
fun Int.toCoordinateOrNull() = if (isInCoordinateRange(this)) Coordinate(this) else null
val Int.Coordinate
    get(): Coordinate = toCoordinate()

