package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T>
    {
        var resList = mutableListOf<T>()
        var source = this.filter { it != null }
        val sourceLen = source.size
        var ind1: Int = 0
        var ind2: Int = 1
        if ( sourceLen == 1 ) return resList.plus(source[ind1]) as List<T>

        var out1: Any = source[ind1]!!
        var out2: Any = source[ind2]!!
        while (ind2 <= source.size ) {
            when {
                out1 == out2 -> {
                    //resList  = resList.plus( out1 as String + out2 as String) as MutableList<T>
                    if(out1 is String)
                        resList = resList.plus(  out1 as String +  out2 as String  )  as MutableList<T>
                        else  resList = resList.plus(  2 * out1 as Int   )  as MutableList<T>
                    ind1 += 2
                    ind2 += 2
                }
                out1 != out2 -> {
                    resList = resList.plus(out1) as MutableList<T>
                    resList = resList.plus(out2) as MutableList<T>
                    ind1 += 2
                    ind2 += 2
                }
            }
            if ( ind1 + 1 > sourceLen  ) return  resList as List<T>
            else if (ind2 + 1 > sourceLen && ind1 + 1 == sourceLen ) return resList.plus(source[ind1]) as List<T>
            else {
                out1=source[ind1]!!
                out2=source[ind2]!!
            }
        }
        return resList as List<T>
    }


