package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * Created by Noah on 4/4/2017.
 */
public class Position implements Serializable {
    private int row;
    private int col;

    /**
     * this class is a representation of position on the towD maze
     * @param row
     * @param col
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    @Override
    public String toString() {
        return "{"+ row +","+col+"}";
    }

    /**
     * deep comperator of position
     * @param a
     * @return boolean resault
     */
    public boolean equalPosition(Position a){
        if(a.getRow()==this.getRow() && a.getCol()==this.getCol())
            return true;
        return false;
    }

}
