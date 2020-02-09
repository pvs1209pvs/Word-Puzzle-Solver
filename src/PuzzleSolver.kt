import kotlin.math.max

class PuzzleSolver(var targetPuzzle: Puzzle, val i: Int) : Thread() {

    private fun horizontalFinder(): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        for (i in targetPuzzle.puzzle.indices) {
            var c: String = targetPuzzle.puzzle[i][0].toString()

            for (j in 1 until targetPuzzle.puzzle[i].size) {
                c += targetPuzzle.puzzle[i][j]
                targetPuzzle.allKeys.forEach {
                    if (c.contains(it) || c.reversed().contains(it)) set += it
                }
            }

        }

        return set

    }


    private fun verticalFinder(): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        for (i in targetPuzzle.puzzle.indices) {
            var c: String = targetPuzzle.puzzle[0][i].toString()

            for (j in targetPuzzle.puzzle[i].indices) {
                c += targetPuzzle.puzzle[j][i]
                targetPuzzle.allKeys.forEach {
                    if (c.contains(it) || c.reversed().contains(it)) set += it
                }
            }

        }

        return set

    }


    fun diagonalFinder(): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        for (i in targetPuzzle.puzzle.indices) {
            for (j in targetPuzzle.puzzle[0].indices) {

                var c: String = ""

                for (k in 0 until targetPuzzle.puzzle.size - max(i, j)) {
                    c += targetPuzzle.puzzle[i + k][j + k]
                    targetPuzzle.allKeys.forEach { if (c.contains(it) || c.reversed().contains(it)) set += it }
                }

            }
        }

        return set

    }


    override fun run() {
        when (i) {
            0 -> (println("Horizontal Words ${horizontalFinder()}"))
            1 -> (println("Vertical Words ${verticalFinder()}"))
            2 -> (println("Diagonal Words ${diagonalFinder()}"))
        }
    }


}
