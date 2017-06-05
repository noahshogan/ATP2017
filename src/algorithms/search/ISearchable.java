package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Merav on 4/5/2017.
 */
public interface ISearchable {
   /**
    * get the start state
    * @return AState
    */
   public AState GetStartState();

   /**
    * get the goal satate
    * @return AState
    */
   public AState GetGoalState();

   /**
    * get all the Possible States that is legal
    * @param currentObject
    * @return AState
    */
   public Stack<AState> getAllPossibleStates(AState currentObject);
}
