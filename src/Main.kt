import java.util.*

fun main(){


    val puzzle = Puzzle(12,12)

    println(puzzle)

    val aPS = PuzzleSolver(puzzle, 0)
    val bPS = PuzzleSolver(puzzle, 1)

    aPS.run()
    bPS.run()




//    println(".nohtyp...".contains("python".reversed()))

}