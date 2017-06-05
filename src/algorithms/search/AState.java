package algorithms.search;

/**
 * Created by Noah on 4/5/2017.
 */
public abstract class AState implements Comparable<AState> {
    protected String state; //the state itself
    protected AState parentState; // parent state
    private int distance; // distance from start, for all kind of mazes and so on

    /**
     *  default constructor
     */
    public AState()
    {
        this.parentState = null;
        this.state = null;
        this.distance = 0;
    }
    /**
     * constructor
     * @param parentState
     */
    public AState(AState parentState)
    {
        this.parentState = parentState;
        this.distance = 0;
        this.state = null;
    }
    public abstract int CompareTo(AState state);
    public abstract boolean Equals(AState obj);
    public String GetState(){return state;}
    public void SetParentState(AState pstate){this.parentState = pstate;}
    public abstract void PrintState();
    public void setDistance(int distance){this.distance = distance;}
    public int getDistance(){return this.distance;}
    public AState getParentState(){return this.parentState;}

}
