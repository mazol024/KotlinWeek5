package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardClass(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardClass(width)

