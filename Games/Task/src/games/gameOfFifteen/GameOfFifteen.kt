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
    var gameList: MutableList<Int> = mutableListOf()
    //var gameList = RandomGameInitializer().initialPermutation


    override fun initialize() {
        gameList = (this.initializer.initialPermutation as List<Int>).toMutableList()
        var idx = 0
        var maxidx = gameList.size
        for (  cell1  in this.board.cellsMap.keys ) {
            if ( idx < maxidx) {
                board.cellsMap.set(cell1, gameList.get(idx)?:null)
                println("idx $idx cell $cell1 value ${gameList.get(idx)}")
                idx++
            }
        }
        println("CellsMap Values ${board.cellsMap.values}\n CellsMap Keys ${board.cellsMap.keys}\n ")
        println("Matrix is ${this.board.cellsMap}")
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        var minV = 1
        for ((_,V) in board.cellsMap.entries) {
            if (minV == V?:0){
                minV ++
                if (minV == 15) return true
            } else {
                return false
            }
        }
        return true
    }

    override fun processMove(direction: Direction) {
        var emptyCell = this.board.getAllCells().find { this.board.cellsMap.get(it) == null }
        when (direction) {
            Direction.RIGHT, Direction.LEFT -> {
               var row = this.board.getRow( emptyCell!!.i,1..4) as MutableList<Cell>
                when (direction){
                    Direction.RIGHT -> {
                        //row.reversed()
//                        var emptyCell = row.find { board.cellsMap.getValue(it) == null }
                        if (emptyCell?.j!! <= row.size && emptyCell?.j!! > 1 ) {
                            board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i,emptyCell.j-1)))
                            board.cellsMap.put(Cell(emptyCell.i ,emptyCell.j-1),null)
                        }
                    }
                    Direction.LEFT -> {

//                        var emptyCell = row.find { board.cellsMap.getValue(it) == null }
                        if (emptyCell?.j!! >= 1 && emptyCell?.j!! < 4) {
                            board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i,emptyCell.j+1)))
                            board.cellsMap.put(Cell(emptyCell.i ,emptyCell.j+1),null)
                        }
                    }
                }
            }
            Direction.DOWN, Direction.UP -> {
               var column = this.board.getColumn(1..4,emptyCell!!.j)
                when (direction) {
                Direction.DOWN -> {
//                    column.reversed()
                    if (emptyCell?.i!! <= column.size && emptyCell?.i!! > 1) {
                        board.cellsMap.put(emptyCell,board.cellsMap.getValue(Cell(emptyCell.i-1,emptyCell.j)))
                        board.cellsMap.put(Cell(emptyCell.i-1 ,emptyCell.j),null)
                    }
                }
                    Direction.UP -> {
                        if (emptyCell?.i!! >= 1 && emptyCell?.i!! < 4) {
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

