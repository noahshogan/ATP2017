package algorithms.mazeGenerators;

/**
 * Created by Noah on 4/3/2017.
 */
public abstract class AMazeGenerator implements IMazeGenerator {
    /**
     * this is an abstract class of maze
     */
    public abstract Maze generate(int rows,int cols) ;

    public long measureAlgorithmTimeMillis(int rows, int column)  {
        long start = System.currentTimeMillis();
        generate(rows, column);
        long end = System.currentTimeMillis();
        return end-start;
    }


}
