package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.*;
import java.util.LinkedList;
/**
 * Created by Merav on 16/5/2017.
 */
public class BestFirstSearch extends ASearchingAlgorithm{

    String name;
    int nodesEvaluated;
    PriorityQueue <AState> queue;
    Priority pr;
    public BestFirstSearch(){
        this.name="BestFirstSearch";
        nodesEvaluated=0;
        pr = new Priority();
        queue=new PriorityQueue<AState>(pr);
    }

    /**
     *
     * @return the number of nodes that send to the open list
     */
    public int getNumberOfNodesEvaluated(){
       return nodesEvaluated;
    }

    /**
     * running the BestFirstSearch algorithm on the ISearchable searchDomain
     * @param searchDomain
     * @return solution to the algorithm
     */
    public Solution solve(ISearchable searchDomain){
        Solution solution= new Solution();

        ArrayList<Position> NodeVisited=new ArrayList();
        MazeState goal=(MazeState) searchDomain.GetGoalState();
        AState start=searchDomain.GetStartState();
        queue.add(start);
        boolean findSolution=false;
        MazeState current;
        this.nodesEvaluated++;
        while(queue.size()!=0 ){

            current=(MazeState)queue.remove();
            //get All Possible States
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
                        }
                        int distance=current.getDistance()+1;
                        p.setDistance(distance);
                        //add the node to the open list(queue)
                        if (!queue.contains(p)){
                            queue.add(p);
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
     * @return  String
     */
    public String getName() {
        return name;
    }
}

/**
 * Comparator to the distance Priority
 */
class Priority implements Comparator<Object>
{

    public int compare(Object o1, Object o2)
    {
        AState a1 = (AState) o1;
        AState a2 = (AState) o2;
        int a = a1.getDistance();
        int b = a2.getDistance();

        return Integer.compare(a, b);
    }
}
