package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * a class representing the solution of the problem.
 */
public class Solution {
    private ArrayList<AState> sol;

    public ArrayList<AState> getSolutionPath(){
        return this.sol;
    }

    /**
     * add AState to the solution
     * @param to_add the parameter we want to add
     */
    public void add_to_solution(AState to_add){
        this.sol.add(to_add);
    }
    public Solution(){
        this.sol = new ArrayList<>();
    }



}
