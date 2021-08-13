package games.game2048

import board.*
import games.game.Game
import board.GameBoardClass

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
        Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board:GameBoardClass<Int?> = createGameBoard<Int?>(4) as GameBoardClass<Int?>

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>)
    {    val initz = initializer.nextValue(this)
        this.set((initz?.first),initz?.second )
    }
/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    if ( rowOrColumn.asSequence()
            .map { (this as GameBoardClass).cellsMap.get(it) }
        .zipWithNext{a, b -> if (a == b || a == null)  false else true }
            .all { it == true }) return false
    var oldValues = mutableListOf<Int?>()
    for ( i in rowOrColumn) {
        oldValues = oldValues.plus((this as GameBoardClass<Int?>)
            .cellsMap.get(i)).filter { it != null }.toMutableList()
    }
    if (oldValues.size == 0 ) return false
    oldValues = (oldValues.moveAndMergeEqual { it.times(2) } as List<Int?>).toMutableList()
    val indx = oldValues.size
    var loop = 0
    for ( i in rowOrColumn) {
        if (loop < indx) {
            (this as GameBoardClass).cellsMap.put(i,oldValues[loop])
            loop++
        } else {
            (this as GameBoardClass).cellsMap.put(i,null)
        }
    }
    return true
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    var moved = false
    when (direction) {
        Direction.RIGHT, Direction.LEFT -> {
            for (row in 1..width) {
                var rowData = this.getRow(row, 1..width)
                if (direction == Direction.RIGHT) {
                    moved = this.moveValuesInRowOrColumn(rowData.reversed()) || moved
                    rowData.reversed()
                } else {
                    moved = this.moveValuesInRowOrColumn(rowData) || moved
                }
            }
        }
        Direction.DOWN, Direction.UP -> {
            for (column in 1..width) {
                var columnData = this.getColumn( 1..width,column)
                if (direction == Direction.DOWN) {
                   moved = this.moveValuesInRowOrColumn(columnData.reversed()) || moved
                    columnData.reversed()
                } else {
                   moved = this.moveValuesInRowOrColumn(columnData) || moved
                }
            }
        }
    }
    return moved
}