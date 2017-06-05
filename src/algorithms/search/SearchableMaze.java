package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.*;

/**
 * Created by Merav on 16/5/2017.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * get the start state
     * @return AState
     */
    public AState GetStartState(){
        MazeState startS =new MazeState(this.maze.getStart());
        return startS;
    }

    /**
     * get the goal state
     * @return AState
     */
    public AState GetGoalState(){
         MazeState goalS=new MazeState(this.maze.getGoalPosition());
         return goalS;
    }

    /**
     * get all the possible states that is legal position
     * @param currentObject
     * @return Stack<AState>
     */
    public Stack<AState> getAllPossibleStates(AState currentObject){
        Stack<AState> neighbors=new Stack<AState>();
        MazeState pos= (MazeState)currentObject;
        Stack<Position> getNeighborsP=this.maze.getNeighbors(pos.getPos());

        if (getNeighborsP.size()!=0 ) {

           while ( !getNeighborsP.isEmpty()){

               Position p=getNeighborsP.pop();
               MazeState newS = new MazeState(p);
               neighbors.push(newS);

           }
        }
        return neighbors;
    }

}
