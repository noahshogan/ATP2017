package algorithms.search;

import java.util.ArrayList;

/**
 * Created by merav on 16/04/2017.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    String name;

    public ASearchingAlgorithm(){
        this.name=null;
    }

    public abstract int getNumberOfNodesEvaluated();
    public abstract Solution solve(ISearchable searchDomain);

    /**
     * get the name of the search
     * @return String
     */
    public abstract String getName();

    /**
     * function that back traces the goal state and adds its predecessors to a solution
     * @param reachGoal
     * @return solution
     */

    public Solution getTheSolution(AState reachGoal){
        Solution solution=new Solution();

        while(reachGoal.getParentState() != null){
            solution.addState(reachGoal);
            reachGoal= reachGoal.getParentState();
        }
        solution.addState(reachGoal);

        return solution;
    }
}
