import java.io.File


class Puzzle(rows: Int, cols: Int) {

    var puzzle: Array<CharArray> = arrayOf()
    var allKeys: Array<String> = arrayOf()

    init {

        for (i in 0 until rows) {
            val puzzleRow = CharArray(cols)
            puzzleRow.fill('.')
            puzzle += puzzleRow
        }

        allKeys = readKeys("/home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/answers")


        //fillEmptySpots()

    } // init

    /*
    ornt: 0 is vertical 1 is horizontal 2 is diagonally
     */
    private fun addKeys(key: String, ornt: Int, reversed: Boolean): String? {

        if (puzzle[0].size < key.length) {
            println("key length ${key.length} > column length ${puzzle[0].size}")
            return null
        }

        if (ornt == 0) {
            return fillVerticallyWithKey(if (reversed) key.reversed() else key)
        } else if (ornt == 1) {
            return fillHorizontallyWithKey(if (reversed) key.reversed() else key)
        } else if (ornt == 2) {
            return fillDiagonallyWithKey(if (reversed) key.reversed() else key)
        }

        return null


    } // addKeys


    /*
    Adds words vertically into the puzzle.
    Returns the words if it could be successfully added else null.
     */
    private fun fillVerticallyWithKey(key: String): String? {

        val r = (0..puzzle.size - key.length).random()
        val c = (puzzle[0].indices).random()

        for (i in key.indices) {
            if (!isEmptyTile(i + r, c)) {
                return null
            }
        }

        for (i in key.indices) {
            puzzle[i + r][c] = key[i]
        }

        return key
    }

    /*
   Adds words horizontally into the puzzle.
   Returns the words if it could be successfully added else null.
    */
    private fun fillHorizontallyWithKey(key: String): String? {

        val r = (puzzle.indices).random()
        val c = (0..puzzle[0].size - key.length).random()

        for (i in key.indices) {
            if (!isEmptyTile(r, c + i)) {
                return null
            }
        }

        for (i in key.indices) {
            puzzle[r][c + i] = key[i]
        }


        return key

    }

    /*
   Adds words diagonally into the puzzle.
   Returns the words if it could be successfully added else null.
    */
    private fun fillDiagonallyWithKey(key: String): String? {

        val r = (0..puzzle.size - key.length).random()
        val c = (0..puzzle[0].size - key.length).random()

        for (i in key.indices) {
            if (!isEmptyTile(r + i, c + i)) {
                return null
            }
        }

        for (i in key.indices) {
            puzzle[r + i][c + i] = key[i]
        }

        return key

    }


    private fun readKeys(fileName: String): Array<String> {

        var keys: Array<String> = arrayOf()

        File(fileName).forEachLine {
            val key = (addKeys(it, (0..2).random(), (0..1).random() == 1))
            if (key != null) keys += it
        }

        return keys

    }

    /*
    Checks if there is an empty spot at the provided index.
    Returns true if the spot is empty else false.
     */
    private fun isEmptyTile(r: Int, c: Int): Boolean {
        return puzzle[r][c] == '.'
    }


    /*
    Fills empty spots will random english alphabets.
     */
    private fun fillEmptySpots() {
        for (i in puzzle.indices) {
            for (j in puzzle[i].indices) {
                if (isEmptyTile(i, j)) {
                    puzzle[i][j] = ('a'..'z').random()
                }
            }
        }
    }

    override fun toString(): String {

        val stringValue: StringBuilder = StringBuilder()

        for (i in puzzle.indices) {
            for (j in puzzle[i].indices) {
                stringValue.append("${puzzle[i][j]}\t")
            }
            stringValue.append('\n')
        }

        stringValue.append("\nLook for:\t")
        allKeys.forEach { stringValue.append("$it ") }

        stringValue.append("\n")

        return stringValue.toString()

    }

}