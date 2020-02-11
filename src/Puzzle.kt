import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.RandomAccessFile
import java.util.concurrent.TimeUnit


class Puzzle(rows: Int, cols: Int) {

    var puzzle: Array<CharArray> = arrayOf()
    var allKeys: Array<String> = arrayOf()

    init {

        for (i in 0 until rows) {
            val puzzleRow = CharArray(cols)
            puzzleRow.fill('.')
            puzzle += puzzleRow
        }

        // allKeys = readKeys("/home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/answers")

        allKeys = newRead("/home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/dump.txt")


        fillEmptySpots()

    } // init

    /*
    ornt: 0 is vertical 1 is horizontal 2 is diagonally
     */
    private fun addKeys(key: String, ornt: Int, reversed: Boolean): String? {

        if (puzzle[0].size < key.length) {
            //println("key length ${key.length} > column length ${puzzle[0].size}")
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


    private fun newRead(fileName: String): Array<String> {

        fetchWordsFromWeb()


        var keys: Array<String> = arrayOf()

        val raFile = RandomAccessFile(fileName, "r")
        raFile.seek(1241)

        var reader = ""
        var seek: Int

        seek = raFile.read()
        reader += seek.toChar()

        while (!reader.contains("Main")) {

            if (seek == 32) {
                reader = reader.trim()
                if (reader.isNotEmpty()) {
                    val key = addKeys(reader, (0..2).random(), (0..1).random() == 1)
                    if (key != null) keys += reader
                }
                reader = ""
            }

            seek = raFile.read()
            reader += seek.toChar()

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
                // print("${i},${j}\t\t")
            }
            stringValue.append('\n')
            //println()
        }

        stringValue.append("\nLook for:\t")
        allKeys.forEach { stringValue.append("$it ") }

        stringValue.append("\n")

        return stringValue.toString()

    }

    private fun fetchWordsFromWeb() {

        val process = ProcessBuilder("bash", "-c", "lynx -dump https://jimpix.co.uk/generators/word-generator.asp > /home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/dump.txt")
        process.redirectErrorStream(true)
        val p: Process= process.start()
        val ret:Int = p.waitFor()
        p.inputStream.reader(Charsets.UTF_8).use {
            println(it.readText())
        }

       p.destroy()

    }



}