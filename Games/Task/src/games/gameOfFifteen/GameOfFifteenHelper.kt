package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    var  counter = 0
    var listOf15 = permutation as MutableList<Int>
    var sizeOfP = listOf15?.size
    for (i1 in 0..sizeOfP!!-2){
        for (i2 in i1+1 ..sizeOfP-1){
            if ( listOf15?.get(i1)!! > listOf15?.get(i2)!! ) {
                var a = listOf15.get(i1)
                listOf15.set(i1, listOf15.get(i2))
                listOf15.set(i2,a)
                counter++
            }
        }
    }
    return if (counter%2 == 0 ) true else false
}

