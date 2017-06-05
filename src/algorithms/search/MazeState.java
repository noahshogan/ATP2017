package algorithms.search;

import algorithms.mazeGenerators.Position;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;

/**
 * Created by Noah on 4/5/2017.
 */
public class MazeState extends AState implements Serializable {
    private Position pos;

    public MazeState(Position pos) {
        this.pos = pos;
    }

    @Override
    public int CompareTo(AState state) {
        throw new NotImplementedException();
    }

    @Override
    public boolean Equals(AState obj) {
        return equals(obj instanceof MazeState);
    }
    @Override
    public void PrintState() {
        pos.toString();
    }

    @Override
    public int compareTo(AState o) {
        return 0;
    }

    @Override
    public String toString() {
        return "MazeState{" +
                "x=" + pos.getRow()+" y=" + pos.getCol() +
                '}';
    }

    /**
     * get the position (row,col)
     * @return Position
     */
    public Position getPos() {
        return pos;
    }
}
