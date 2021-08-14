package games.gameOfFifteen

import java.util.stream.IntStream
import kotlin.random.Random

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation: List<Int> = IntArray(1000){  Random.nextInt(1,16)}.distinct().take(15)
        .distinct()

//
//    override val initialPermutation: List<Int> by lazy {
//      //funcInitialize()
//        println("Lazy List generating")
//        generateSequence(Random.nextInt(1, 16),{  Random.nextInt(1, 16)} )
//            .distinct()
//            .toList()
//            .take(15)
//                    }
//
//    fun funcInitialize(): List<Int> =
//         generateSequence({ Random.nextInt(1, 16)} )
//             .distinct()
//             .toList()
//             .take(15)

}

