package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * solving a maze class according to Best First Search algorithm.
 */
public class BestFirstSearch extends ASearchingAlgorithm{

    public String getName(){
        return "BestFirstSearch";
    }

    /**
     * solving a maze with Best_fs function
     * @param iS is the object which searching on it.
     * @return the solution of the search.
     */
    public Solution solve(ISearchable iS){
        if(iS == null || iS.getStartState() == null)
            iS = new SearchableMaze(new Maze(30,30));
        this.sol = Best_fs(iS, iS.getStartState());
        return this.sol;
    }

    /**
     * the main function which actually solving the problem
     * @param iS is the object which searching on it.
     * @param curr is the current situation, the state state for start.
     * @return the final solution according to the algorithm.
     */
    private Solution Best_fs(ISearchable iS, AState curr) {
        if(iS == null || curr == null)
            return null;
        boolean visited[][] = new boolean[iS.get_rows_size()][iS.get_col_size()];
        Comparator<AState> comparator = new  AState_Comperator();
        PriorityQueue<AState> pq = new PriorityQueue<>(comparator);
        pq.add(curr);
        AState n = curr;
        visited[curr.getRowIndex()][curr.getColumnIndex()] = true;
        while (!n.equals(iS.getGoalState()) && pq.size()!=0) {
            curr = pq.poll();
            this.num_of_nude++;
            Iterator<AState> i = iS.getAllPossibleStates(curr).listIterator();
            while (i.hasNext()) {
                n = i.next();
                if (!visited[n.getRowIndex()][n.getColumnIndex()]) {
                    visited[n.getRowIndex()][n.getColumnIndex()] = true;
                    pq.add(n);
                    n.Add_came_from(curr);
                }
                if(n.equals(iS.getGoalState())){
                    break;
                }


            }

        }
        n.Add_came_from(curr);
        AState pointer = n;
        Solution ans = new Solution();
        /**
         * restore the path
         */
        while (!pointer.equals(iS.getStartState())) {
            ans.add_to_solution(pointer);
            pointer = pointer.get_came_from();
        }
        ans.add_to_solution(pointer);
        Collections.reverse(ans.getSolutionPath());

        return ans;

    }
}
