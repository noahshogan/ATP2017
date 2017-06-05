package algorithms.search;

/**
 * Created by merav on 16/04/2017.
 */
public interface ISearchingAlgorithm {
    /**
     * get the number of nodes that were send to the open list
     * @return int
     */
    public int getNumberOfNodesEvaluated();

    /**
     * get the solution of a search algorithm
     * @param searchDomain
     * @return Solution
     */
    public Solution solve(ISearchable searchDomain);

    /**
     * get the name of the Searching Algorithm
     * @return String
     */
    public String getName();
}
