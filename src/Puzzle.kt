import java.io.RandomAccessFile

class Puzzle(rows: Int, cols: Int) {

    var puzzle: Array<CharArray> = Array(rows) { CharArray(cols) { '.' } }
    var allKeys: Array<String> = arrayOf()

    init {

        println("Loading....")
        for (i in 0 until 10) {
            allKeys += readKeysFromFile("/home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/dump.txt")
        }
        fillEmptySpots()

    }


    /**
     * @param key word that goes in the puzzle.
     * @param ornt in what orientation is the word added. 0 for horizonal, 1 for verticla and 2 for diagonal.
     * @param reversed if the word has to added in reverse.
     * @return the key that on successful addition else null.
     */
    private fun addKeys(key: String, ornt: Int, reversed: Boolean): String? {

        if (puzzle[0].size < key.length) return null

        return when (ornt) {
            0 -> fillVerticallyWithKey(if (reversed) key.reversed() else key)
            1 -> fillHorizontallyWithKey(if (reversed) key.reversed() else key)
            2 -> fillDiagonallyWithKey(if (reversed) key.reversed() else key)
            else -> null
        }

    }


    /**
     * Generates a random row and column number where the key needs to added vertically.
     * @param key the word that needs to be added to the puzzle.
     * @return the key upon successful addition and null on a failed addition.
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

    /**
     * Generates a random row and column number where the key needs to added horizontally.
     * @param key the word that needs to be added to the puzzle.
     * @return the key upon successful addition and null on a failed addition.
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

    /**
     * Generates a random row and column number where the key needs to added diagonally.
     * @param key the word that needs to be added to the puzzle.
     * @return the key upon successful addition and null on a failed addition.
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


    /**
     * @param fileName path of the file containing the randomly generated words.
     * @return Array of words that were added to the puzzle.
     */
    private fun readKeysFromFile(fileName: String): Array<String> {

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
                    if (key != null && key.length > 3) keys += reader
                }
                reader = ""
            }

            seek = raFile.read()
            reader += seek.toChar()

        }

        return keys

    }


    /**
     * Checks if there is an empty spot at the provided indices.
     * @param r row number.
     * @param c column number.
     * @return true if there is an empty stop at r,c.
     */
    private fun isEmptyTile(r: Int, c: Int): Boolean {
        return puzzle[r][c] == '.'
    }


    /**
     * Adds random english alphabets at empty indices.
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

    /**
     * Dumps https://jimpix.co.uk/generators/word-generator.asp to the file.
     */
    private fun fetchWordsFromWeb() {

        val process = ProcessBuilder(
            "bash",
            "-c",
            "lynx -dump https://jimpix.co.uk/generators/word-generator.asp > /home/param/Desktop/Kotlin-Projects/Word-Puzzle/src/dump.txt"
        )
        process.redirectErrorStream(true)

        val p: Process = process.start()
        p.inputStream.reader(Charsets.UTF_8).use {
           // println(it.readText())
        }

        p.destroy()

    }


    override fun toString(): String {

        val stringValue: StringBuilder = StringBuilder()

        for (i in puzzle.indices) {
            for (j in puzzle[i].indices) {
                stringValue.append("${puzzle[i][j]}\t")
            }
            stringValue.append('\n')
        }

        stringValue.append("\nLook for ${allKeys.size} words:  ")

        allKeys.forEach { stringValue.append("$it ") }

        stringValue.append("\n")

        return stringValue.toString()

    }


}