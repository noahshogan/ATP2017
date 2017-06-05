package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Merav on 4/5/2017.
 */
public class Solution  implements Serializable {

    private ArrayList solutionPath;

    /**
     *  default constructor
     */
    public Solution(){
        solutionPath=new ArrayList();
    }

    /**
     * constructor
     * @param arraySol
     */
    public Solution(ArrayList arraySol){
        solutionPath=arraySol;
    }

    public void addState(AState s){
        this.solutionPath.add(s);
    }

    /**
     * check if their is a solution
     * @return boolean
     */
    public boolean IsSolExists(){
        if (solutionPath.size() > 0) return true;
        else return false;
    }

    /**
     *  returns the solotion path as an ArrayList.
     * @return Array list
     */
    public ArrayList getSolutionPath(){
        return solutionPath;
    }

    /**
     * print the steps of the solution
     */
    public void printSolutionPath(){

        if(solutionPath==null && solutionPath.size()==0){
            System.out.println("There is no solution yet");
        }
        else for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(solutionPath.get(i).toString());
        }

    }

    /**
     *  returns the  number of Steps of the solution
     * @return int
     */
    public int getSolutionSteps(){
        return this.solutionPath.size();
    }

}
