class PuzzleSolver(var targetPuzzle: Puzzle, val i:Int) : Thread() {

    fun horizontalFinder(): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        for (i in targetPuzzle.puzzle.indices) {
            var c: String = targetPuzzle.puzzle[i][0].toString()
            for (j in 1 until targetPuzzle.puzzle[i].size) {
                c += targetPuzzle.puzzle[i][j]
                for (x in targetPuzzle.lookFor) {
                    if (c.contains(x) || c.contains(x.reversed())) {
                        set += if (targetPuzzle.allKeys.contains(x)) x else x.reversed()
                    }
                }
            }

        }

        return set

    }

    fun verticalFinder(): MutableSet<String> {

        val set: MutableSet<String> = mutableSetOf()

        for (i in targetPuzzle.puzzle.indices) {
            var c: String = targetPuzzle.puzzle[0][i].toString()
            for (j in targetPuzzle.puzzle[i].indices) {
                c += targetPuzzle.puzzle[j][i]
                for (x in targetPuzzle.lookFor) {
                    if (c.contains(x) || c.contains(x.reversed())) {
                        set += if (targetPuzzle.allKeys.contains(x)) x else x.reversed()
                    }
                }
            }

        }

        return set

    }


    override fun run() {
        if(i==0) println(horizontalFinder())
        else println(verticalFinder())
    }



}
