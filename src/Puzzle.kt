import java.io.File

/*
    00  01  02  03  04  05
    10  11  12  13  14  15
 */

class Puzzle(rows: Int, cols: Int) {

    var puzzle: Array<CharArray> = arrayOf()
    var keys: Array<String> = arrayOf()

    init {

        for (i in 0 until rows) {
            val puzzleRow = CharArray(cols)
            puzzleRow.fill('.')
            puzzle += puzzleRow
        }

        keys = readKeys("/home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/answers")

        for (x in keys) {
            addKeys(x, (0..2).random(), (0..1).random() == 1)
        }

        fillEmpty()

    } // init

    /*
    ornt: 0 is vertical 1 is horizontal 2 is diagonally
     */
    fun addKeys(key: String, ornt: Int, reversed: Boolean) {

        if (puzzle[0].size < key.length) {
            println("key length ${key.length} > column length ${puzzle[0].size}")
            return
        }

        if (ornt == 0) {
            fillVerticallyWithKey(if (reversed) key.reversed() else key)
        } else if (ornt == 1) {
            fillHorizontallyWithKey(if (reversed) key.reversed() else key)
        } else if (ornt == 2) {
            fillDiagonallyWithKey(if (reversed) key.reversed() else key)
        }


    } // addKeys


    private fun fillVerticallyWithKey(key: String) {

        val r = (0..puzzle.size - key.length).random()
        val c = (puzzle[0].indices).random()

        for (i in key.indices) {
            if (!isEmptyTile(i + r, c)) {
                return
            }
        }

        for (i in key.indices) {
            puzzle[i + r][c] = key[i]
        }

    }

    private fun fillHorizontallyWithKey(key: String) {

        val r = (puzzle.indices).random()
        val c = (0..puzzle[0].size - key.length).random()

        for (i in key.indices) {
            if (!isEmptyTile(r, c + i)) {
                return
            }
        }

        for (i in key.indices) {
            puzzle[r][c + i] = key[i]
        }

    }

    private fun fillDiagonallyWithKey(key: String) {

        var r = (0..puzzle.size - key.length).random()
        val c = (0..puzzle[0].size - key.length).random()

        for (i in key.indices) {
            if (!isEmptyTile(r + i, c + i)) {
                return
            }
        }

        for (i in key.indices) {
            puzzle[r + i][c + i] = key[i]
        }


    }

    private fun isEmptyTile(r: Int, c: Int): Boolean {
        return puzzle[r][c] == '.'
    }

    private fun fillEmpty() {
        for (i in puzzle.indices) {
            for (j in puzzle[i].indices) {
                if (isEmptyTile(i, j)) {
                   puzzle[i][j] = ('a'..'z').random()
                }
            }
        }
    }

    fun readKeys(fileName: String): Array<String> {

        var ans: Array<String> = arrayOf()

        File(fileName).forEachLine {
            ans += it
        }

        return ans

    }

    override fun toString(): String {

        val stringValue: StringBuilder = StringBuilder()

        for (i in puzzle.indices) {
            for (j in puzzle[i].indices) {
                stringValue.append("${puzzle[i][j]}\t")
            }
            stringValue.append('\n')
        }
        return stringValue.toString()

    }

}