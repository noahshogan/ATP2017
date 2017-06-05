package algorithms.mazeGenerators;

import java.util.Random;

/**
 * Created by Noah on 4/4/2017.
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    Position start,end;
    Random rnd;
    int[][] maze;
    @Override
    /**
     * generates a random maze
     */
    public Maze generate(int rows, int cols) {
        maze = new int[rows][cols];
        rnd = new Random();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
            if (i==0){
                maze[i][j] = WallType.wall;
            }else if(j==0){
                maze[i][j] = WallType.wall;
            }else if(i==rows-1){
                maze[i][j] = WallType.wall;
            }
            else if(j==cols-1){
                maze[i][j] = WallType.wall;
            }else{
                maze[i][j] = WallType.passage;
            }
        }
        for (int i = 0; i < rows; i++){
            maze[i][rnd.nextInt(cols-2)+1] = WallType.wall;
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
                //maze[i][0]='\u2591';
                //maze[i][0]='S';
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

}


