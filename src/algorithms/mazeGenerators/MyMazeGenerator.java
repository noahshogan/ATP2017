package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Noah on 4/4/2017.
 */
public class MyMazeGenerator extends AMazeGenerator {
    /**
     * This class represent the maze
     * @param maze this is the towD array of int
     * @param visited  towD boolean that is boolean representation of the original maze
     * @param walls  array list al all the walls on the maze
     * @param start  starting position of the maze
     * @param end  ending position of the maze
     * @param myPos  my position on the maze
     * @return Maze returns new maze.
     */
    int[][] maze;
    boolean[][] visited;
    Random rnd;
    ArrayList<Position> walls;
    Position start,end;

    /**
     * this is the maze generator that creates maze
     * @return maze;
     */
    public Maze generate(int rows, int cols){
        maze = new int[rows][cols];
        visited= new boolean[rows][cols];
        rnd = new Random();
        walls = new ArrayList<Position>();
        //--------------fill grid with walls
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                maze[i][j] = WallType.wall;
                visited[i][j] = false;
            }
        int r = rnd.nextInt(rows-2)+1;
        int c = rnd.nextInt(cols-2)+1;
        maze[r][c]=WallType.passage;
        visited[r][c]=true;
        getNighborWalls(r,c);
        while(walls.size()>0){//While there are walls in the list:
            //Pick a random wall from the list
            Position tmp = walls.remove(rnd.nextInt(walls.size()));
            // If only one of the two cells that the wall divides is visited
            if(checkIfOneOfNightborIsVisited(tmp)){
                //Make the wall a passage and mark the unvisited cell as part of the maze.
                maze[tmp.getRow()][tmp.getCol()]=WallType.passage;
                visited[tmp.getRow()][tmp.getCol()]=true;
                //Add the neighboring walls of the cell to the wall list.
                getNighborWalls(tmp.getRow(),tmp.getCol());
            }
        }
        setStartAndEndPoints();
        Maze ans = new Maze(maze);
        ans.setEnd(end);
        ans.setMyPos(start);
        ans.setStart(start);
        return ans;
    }

    /**
     * Set randomly start and end point to the maze;
     */
    private void setStartAndEndPoints() {
        while(true){
            int i = rnd.nextInt(maze.length);
            if (maze[i][1]==WallType.passage){
                start = new Position(i,0);
                break;
            }
        }
        while(true){
            int i = rnd.nextInt(maze.length);
            if (maze[i][maze[0].length-2]==WallType.passage){
                //maze[i][maze[0].length-1]='E';
                end = new Position(i,maze[0].length-1);
                break;
            }
        }
    }
    /**
     * part og the Generator returns the legal Nighbors (if exist) of given position
     */
    private void getNighborWalls(int r, int c) {
        if (r+2<=maze.length&&visited[r+1][c]==false)
            walls.add(new Position(r+1,c));
        if (r-2>=0&&visited[r-1][c]==false)
            walls.add(new Position(r-1,c));
        if (c+2<=maze[0].length&&visited[r][c+1]==false)
            walls.add(new Position(r,c+1));
        if (c-2>=0&&visited[r][c-1]==false)
            walls.add(new Position(r,c-1));
    }

    private boolean checkIfOneOfNightborIsVisited(Position tmp) {
        int x = tmp.getRow();
        int y = tmp.getCol();
        if (x+1<maze.length&&x-1>=0&&y+1<maze[0].length&&y-1>=0){
            if (visited[x+1][y]==true&&visited[x-1][y]==false&&visited[x][y+1]==false&&visited[x][y-1]==false&&visited[x-1][y+1]==false&&visited[x-1][y-1]==false)return true;
            if (visited[x+1][y]==false&&visited[x-1][y]==true&&visited[x][y+1]==false&&visited[x][y-1]==false&&visited[x+1][y+1]==false&&visited[x+1][y-1]==false)return true;
            if (visited[x+1][y]==false&&visited[x-1][y]==false&&visited[x][y+1]==true&&visited[x][y-1]==false&&visited[x+1][y-1]==false&&visited[x-1][y-1]==false)return true;
            if (visited[x+1][y]==false&&visited[x-1][y]==false&&visited[x][y+1]==false&&visited[x][y-1]==true&&visited[x+1][y+1]==false&&visited[x-1][y+1]==false)return true;
        }
        return false;
    }
}
