package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;
import java.util.LinkedList;


/**
 * Created by Merav on 16/5/2017.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm{

    String name;
    LinkedList <AState> queue;
    private int nodesEvaluated;

    public BreadthFirstSearch(){
        this.name="BreadthFirstSearch";
        queue= new LinkedList<AState>();
        nodesEvaluated=0;
    }
    /**
     *
     * @return the number of nodes that send to the open list(queue)
     */
    public int getNumberOfNodesEvaluated(){
        return nodesEvaluated;
    }

    /**
     * running the BreadthFirstSearch algorithm on the ISearchable searchDomain
     * @param searchDomain
     * @return solution to the algorithm
     */
    public Solution solve(ISearchable searchDomain){

        ArrayList<Position> NodeVisited=new ArrayList();
        Solution solution=new Solution();
        MazeState goal=(MazeState) searchDomain.GetGoalState();
        AState start=searchDomain.GetStartState();
        queue.addLast(start);
        boolean findSolution=false;
        MazeState current;
        this.nodesEvaluated++;
        while(queue.size()!=0 ){

            current=(MazeState)queue.removeFirst();
            this.nodesEvaluated++;
            Stack<AState> allNeighbors=searchDomain.getAllPossibleStates(current);
            if(allNeighbors !=null || !(allNeighbors.isEmpty())) {

                while ( !(allNeighbors.isEmpty())){

                    MazeState p=(MazeState) allNeighbors.pop();
                    Position newP=p.getPos();
                    //check if we already been in this allNeighbor
                    boolean checkP=false;
                    for(Position pos:NodeVisited){

                        if(newP.equalPosition(pos)){
                            checkP=true;
                            break;
                        }
                    }
                    if (!checkP) {
                        p.SetParentState(current);
                        Position Rgoal=goal.getPos();
                        //check if we reach to the goal state
                        if (p.getPos().equalPosition(Rgoal)) {
                            solution = getTheSolution(p);
                            findSolution = true;
                            break;
                        }   //add the node to the open list(queue)
                        if (!queue.contains(p)){
                            queue.addLast(p);
                            this.nodesEvaluated++;
                        }
                    }
                }// if the allNeighbor not in the array list NodeVisited add it
                if (!(NodeVisited.contains(current))) {
                    NodeVisited.add(current.getPos());
                }
            }
        }
        return solution;
    }

    /**
     * get the name of the search
     * @return String
     */
    public String getName(){
        return this.name;
    }
}
