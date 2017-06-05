package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) throws InterruptedException {
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator mazeGenerator) throws InterruptedException {
        // prints the time it takes the algorithm to run
        Position p = new Position(30,30);
        System.out.println(String.format("Maze generation time(ms): %s",
                mazeGenerator.measureAlgorithmTimeMillis(p.getRow()/*rows*/,p.getCol()/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(p.getRow()/*rows*/,p.getCol()/*columns*/);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the position
        System.out.println(String.format("Start Position: %s",
                startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s",
                maze.getGoalPosition()));
    }
}


