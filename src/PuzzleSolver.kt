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
            var z = 0
            for (j in i until targetPuzzle.puzzle[i].size-1) {
                val x = z++
                val y = j + 1
                for (k in x until y) {
                print("${x},${y} ")
                }
                println()
            }
            println()
            println()
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
