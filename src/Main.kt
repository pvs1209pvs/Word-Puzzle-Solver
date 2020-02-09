import java.util.concurrent.TimeUnit

fun main(){

    val puzzle = Puzzle(50,50)

    println(puzzle)

    val aPS = PuzzleSolver(puzzle, 0)
    val bPS = PuzzleSolver(puzzle, 1)
    val cPS = PuzzleSolver(puzzle, 2)




    val startTime:Long = System.nanoTime()
    println("Number of words to look for ${puzzle.allKeys.size}")

    aPS.start()
    bPS.start()
    cPS.start()

    aPS.join()
    bPS.join()
    cPS.join()

    var duration:Long = System.nanoTime()-startTime
    duration = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS)
    println("Time taken $duration milliseconds")

}