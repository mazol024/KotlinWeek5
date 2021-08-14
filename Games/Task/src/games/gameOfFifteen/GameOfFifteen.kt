package games.gameOfFifteen

import board.*
import games.game.Game
import games.game2048.moveValues
import games.game2048.moveValuesInRowOrColumn

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

class GameOfFifteen(val initializer: GameOfFifteenInitializer ):Game {

    private val board: GameBoardClass<Int?> = createGameBoard<Int?>(4) as GameBoardClass<Int?>
    //var gameList: MutableList<Int> = mutableListOf()
    override fun initialize() {
        println("Strting...")
        var gameList = RandomGameInitializer().initialPermutation
//         while (! isEven(gameList) && gameList.size < 16 ) {
//             gameList = RandomGameInitializer().initialPermutation
//         }
        println("All numbers $gameList and size ${gameList.size}")
        var idx = 0
        var maxidx = gameList.size
        for (  cell1  in this.board.cellsMap.keys ) {
            if ( idx < maxidx) {
                board.cellsMap.set(cell1, gameList.get(idx)?:null)
                println("idx $idx cell $cell1 value ${gameList.get(idx)}")
                idx++
            }
                //this.board.cellsMap.set(cell1, null)
        }
        println("CellsMap Values ${board.cellsMap.values}\n CellsMap Keys ${board.cellsMap.keys}\n ")
        println("Matrix is ${this.board.cellsMap}")
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        return this.board
            .cellsMap.values
            .asSequence()
            .zipWithNext()
            .filter {pair -> pair.first?:0 < pair.second?:0 }
            .take(15).any { false }
//        return if (this.board.cellsMap
//            .values
//            .zipWithNext()
//            .filter { pair -> if (pair.first!! < pair.second!! && pair.first!! > 0 ) true else false}
//            .take(15)
//            .all { true }) true else false
    }

    override fun processMove(direction: Direction) {
        var emptyCell = this.board.getAllCells().find { this.board.cellsMap.get(it) == null }
        when (direction) {
            Direction.RIGHT, Direction.LEFT -> {
               var row = this.board.getRow( emptyCell!!.i,1..4) as MutableList<Cell>
                when (direction){
                    Direction.RIGHT -> {
                        row.reversed()
                        var emptyCell = row.find { board.cellsMap.getValue(it) == null }
                        if (emptyCell?.i!! < row.size) {
                            board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i+1,emptyCell.j)))
                            board.cellsMap.put(Cell(emptyCell.i+1 ,emptyCell.j),null)
                        }
                    }
                    Direction.LEFT -> {

                        var emptyCell = row.find { board.cellsMap.getValue(it) == null }
                        if (emptyCell?.i!! < row.size) {
                            board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i+1,emptyCell.j)))
                            board.cellsMap.put(Cell(emptyCell.i+1 ,emptyCell.j),null)
                        }
                    }
                }
            }
            Direction.DOWN, Direction.UP -> {
               var column = this.board.getColumn(1..4,emptyCell!!.j)
                when (direction) {
                Direction.DOWN -> {
                    column.reversed()
                    if (emptyCell?.i!! < column.size) {
                        board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i+1,emptyCell.j)))
                        board.cellsMap.put(Cell(emptyCell.i+1 ,emptyCell.j),null)
                    }
                }
                    Direction.UP -> {
                        if (emptyCell?.i!! < column.size) {
                            board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i+1,emptyCell.j)))
                            board.cellsMap.put(Cell(emptyCell.i+1 ,emptyCell.j),null)
                    }

                }
                }
            }
        }

    }

    override fun get(i: Int, j: Int): Int? =
        this.board.run { get(getCell(i, j)) }

}

