package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Merav on 16/5/2017.
 */
public class DepthFirstSearch extends ASearchingAlgorithm{

    String name;
    int nodesEvaluated;

    public DepthFirstSearch(){

        this.name="DepthFirstSearch";
        this.nodesEvaluated=0;
    }
    /**
     *
     * @return the number of nodes that send to the Stack
     */
    public int getNumberOfNodesEvaluated(){
        return nodesEvaluated;
    }


    /**
     * running the DepthFirstSearch algorithm on the ISearchable searchDomain
     * @param searchDomain
     * @return solution to the algorithm
     */
    public Solution solve(ISearchable searchDomain){
        Solution solution=new Solution();
        Stack<AState> StackNotOpen=new Stack<>();
        MazeState goal=(MazeState) searchDomain.GetGoalState();
        AState start=searchDomain.GetStartState();
        boolean findSolution=false;
        ArrayList<Position> NodeVisited=new ArrayList();
        MazeState current;
        StackNotOpen.add(start);
        this.nodesEvaluated++;
        int i=1;
        MazeState tempcurrent = null;
        Position Rgoal=goal.getPos();
        current=(MazeState)StackNotOpen.pop();
        if (((MazeState)start).getPos()==Rgoal){
            solution = getTheSolution(current);
            findSolution = true;
        }
        while(!findSolution){
            Stack<AState> allNeighbors=searchDomain.getAllPossibleStates(current);
           //check if we already been in this allNeighbor
            boolean checkP = false;
            for (Position pos : NodeVisited) {
                if (current.getPos().equalPosition(pos)) {
                    checkP = true;
                    break;
                }
            }
            if(allNeighbors !=null && !(allNeighbors.isEmpty()) && !checkP){

                while ( !(allNeighbors.isEmpty())) {
                        MazeState p = (MazeState) allNeighbors.pop();
                        p.SetParentState(current);
                        //get the first child
                        if (i == 1) {
                            if (!(NodeVisited.contains(current))) {
                                NodeVisited.add(current.getPos());

                            } //check the child is the goal state
                            if (current.getPos().equalPosition(goal.getPos())) {
                                solution = getTheSolution(current);
                                findSolution = true;
                                break;
                            }
                            tempcurrent=p;
                            i = 2;
                        } else {//get all the children (except the first)
                            //if not the first node to the currnet
                            boolean checkNP = false;
                            for(AState s :StackNotOpen){
                                if( p.getPos().equalPosition(((MazeState)s).getPos()))
                                    checkNP=true;
                                break;
                            }
                            if (!checkNP ) {
                                StackNotOpen.push(p);
                                this.nodesEvaluated++;
                            }
                        }
                }
                current=tempcurrent;
                //check the current is the goal state
                if (current.getPos().equalPosition(goal.getPos())) {
                    solution = getTheSolution(current);
                    findSolution = true;
                    break;
                }
            }
            else{  //check the current is the goal state
                if (current.getPos().equalPosition(goal.getPos())) {
                    solution = getTheSolution(current);
                    findSolution = true;
                    break;
                }
                //get a child from the open list(StackNotOpen)
                if(!StackNotOpen.isEmpty())
                current=(MazeState)StackNotOpen.pop();
                else{
                    System.out.println("There is no Solution");
                    break;
                }
            }
            i=1;
        }
        return solution;
    }

    /**
     * get the name of the search
     * @return String
     */
    public String getName() {
        return this.name;
    }


}
