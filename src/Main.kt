import java.util.concurrent.TimeUnit

fun main(){

    val puzzle = Puzzle(10,10)

    println(puzzle)

//    val aPS = PuzzleSolver(puzzle, 0)
//    val bPS = PuzzleSolver(puzzle, 1)
//
//    val startTime:Long = System.nanoTime()
//    println(puzzle.lookFor.size)
//
//    aPS.start()
//    bPS.start()
//
//    aPS.join()
//    bPS.join()
//
//    var duration:Long = System.nanoTime()-startTime
//    duration = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS)
//    println("duration $duration")

}