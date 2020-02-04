fun horizontalFinder(p: Puzzle) {

    for (i in p.puzzle.indices) {
        var c: String = p.puzzle[i][0].toString()
        for (j in p.puzzle[i].indices) {
            c += p.puzzle[i][j]
            for (x in p.lookFor) {
                if (c.contains(x) || c.contains(x.reversed())) {
                    println(x)
                }
            }
        }

    }

}

fun verticalFinder(p: Puzzle) {

    for (i in p.puzzle.indices) {
        var c: String = p.puzzle[0][i].toString()
        for (j in p.puzzle[i].indices) {
            c += p.puzzle[j][i]
            for (x in p.lookFor) {
                if (c.contains(x) || c.contains(x.reversed())) {
                    println(x)
                }
            }
        }

    }

}

fun diagonalFinder(p: Puzzle) {

    for (i in p.puzzle.indices) {
        var c: String = p.puzzle[0][i].toString()
        for (j in p.puzzle[i].indices) {
            c += p.puzzle[j][i]
            for (x in p.lookFor) {
                if (c.contains(x) || c.contains(x.reversed())) {
                    println(x)
                }
            }
        }

    }

}

