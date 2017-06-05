package algorithms.mazeGenerators;

/**
 * Created by Noah on 4/3/2017.
 */
public interface IMazeGenerator {
    public Maze generate(int rows,int cols) throws InterruptedException;
    public long measureAlgorithmTimeMillis(int rows,int cols) throws InterruptedException;

}
