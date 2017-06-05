package algorithms.mazeGenerators;


import java.io.Serializable;
import java.util.*;

/**
 * Created by Noah on 4/3/2017.
 */


public class Maze implements Serializable{
    /**
     * This class represent the maze
     * @param maze this is the towD array of int
     * @param rows  size of rows
     * @param cols  size of columns
     * @param start  starting position of the maze
     * @param end  ending position of the maze
     * @param myPos  my position on the maze
     * @return Maze returns new maze.
     */
    public int[][] maze;
    private int rows;
    private int cols;
    private Position start;
    private Position end;
    private Position myPos;

    public Maze(int[][] maze){
        this.maze=maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }
    /**
     * This method prints the maze
     * @return nothing.
     */
    public void print(){
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (start.getCol()==j&&start.getRow()==i)
                    System.out.print('S');
                else if (end.getCol()==j&&end.getRow()==i)
                    System.out.print('E');
                else if (maze[i][j]==1)
                    System.out.print('\u2588');
                else if (maze[i][j]==0)
                    System.out.print('\u2591');
                if (j!=cols-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        //System.out.println("-------");
    }
    public Position getGoalPosition(){
        return this.end;
    }
    public Position getStartPosition(){
        return this.start;
    }
    public void setStart(Position start) {
        this.start = start;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public void setMyPos(Position myPos) {
        this.myPos = myPos;
    }

    public Position getStart() {///////////////////////////////////////////////

        return start;
    }

    public Position getEnd() {
        return end;
    }

    public Position getMyPos() {
        return myPos;
    }

    public Maze(byte[] arr){
        setStart(new Position(arr[0]& (0xff),arr[1]& (0xff)));
        setEnd(new Position(arr[2]& (0xff),arr[3]& (0xff)));
        rows=arr[4]& (0xff);
        cols=arr[5]& (0xff);
        maze = new int[rows][cols];
        int counter=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = (int)arr[(counter++)+6];
            }
        }
    }

    public byte[] toByteArray(){
        // arr = (startX,StartY,EndX,EndY,cols,rows)
        byte[] arr = new byte[cols*rows+7];
        arr[0]=(byte)(start.getRow());//0
        arr[1]=(byte)(start.getCol());//1
        arr[2]=(byte)(end.getRow());//2
        arr[3]=(byte)(end.getCol());//3
        arr[4]=(byte)(rows);//4
        arr[5]=(byte)(cols);//5
        int counte=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[(counte++)+6]=(byte)(maze[i][j]);
            }
        }
        arr[arr.length-1]=(byte)9999;
        return arr;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMaze() {
        return maze;
    }
    /**
     * get all 4 Neighbors of given position
     * @return Stack of Positions.
     */
    public Stack<Position> getAllNeighborPosition(Position p){
        Stack<Position> stackP=new Stack<Position>();
        Position up=new Position(p.getRow()-1,p.getCol());
        stackP.add(up);
        Position down=new Position(p.getRow()+1,p.getCol());
        stackP.add(down);
        Position right=new Position(p.getRow(),p.getCol()+1);
        stackP.add(right);
        Position left=new Position(p.getRow(),p.getCol()-1);
        stackP.add(left);
        return stackP;
    }
    /**
     * boolean function that checks if a given position is wall or passeg
     * @return True or False.
     */
    private boolean isLegalPositon(Position checkPosition){
        if (checkPosition.equalPosition(start)||checkPosition.equalPosition(end))
            return true;
        if(checkPosition.getRow()<0 || checkPosition.getCol()<0 || checkPosition.getRow()>=this.rows || checkPosition.getCol() >= this.cols){
            return false;
        }

        if(maze[checkPosition.getRow()][checkPosition.getCol()]== WallType.wall){
            return false;
        }
        return true;
    }

    public Stack<Position> getNeighbors(Position findNeighbors){

        Stack<Position> neibPosition=new Stack<Position>();
        Stack<Position> p=getAllNeighborPosition(findNeighbors);
        Position currnet;
        while(!p.empty()){
            currnet=p.pop();

            if(isLegalPositon(currnet)==true){
                Position enter=new Position(currnet.getRow(),currnet.getCol());
                neibPosition.push(enter);
            }
        }
        return neibPosition;
    }


}
