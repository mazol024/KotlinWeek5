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
 * You can find more examples in 'Test
 * Game2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T>
    {
        var resList = mutableListOf<T>()
        var source = this
            .toMutableList()
            .filter { it != null } as MutableList<T>

        val sourceLen = source.size
        var listIndex: Int = 0
        if ( sourceLen == 1 ) return source as List<T>
        if ( sourceLen == 2 ) {
            when {
                source[0] == source[1] -> resList = resList.plus(merge(source[0] as T)) as MutableList<T>
                else -> {
                    resList = resList.plus(source[0] as T) as MutableList<T>
                    resList = resList.plus(source[1] as T) as MutableList<T>
                }
            }
            return resList
        }

            lateinit var firstElement :T
            lateinit var secondElement : T
            do {
                firstElement = source.get(listIndex)
                secondElement = source.get(listIndex+1)
                when{
                    firstElement === secondElement -> {
                        resList = resList.plus(merge(firstElement) as T) as MutableList<T>
                        listIndex += 2
                    }
                    firstElement != secondElement -> {
                        resList = resList.plus(firstElement as T) as MutableList<T>
                        listIndex +=1
                    }
                }
            } while (listIndex < source.size - 1)
            if (listIndex < source.size ) resList = resList.plus(source.last()) as MutableList<T>
            return resList as List<T>
        }
