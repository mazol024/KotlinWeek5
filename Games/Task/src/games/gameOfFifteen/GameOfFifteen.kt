package games.gameOfFifteen

import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

class GameOfFifteen(val initializer: GameOfFifteenInitializer ):Game {
    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun canMove(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasWon(): Boolean {
        TODO("Not yet implemented")
    }

    override fun processMove(direction: Direction) {
        TODO("Not yet implemented")
    }

    override fun get(i: Int, j: Int): Int? {
        TODO("Not yet implemented")
    }
}

