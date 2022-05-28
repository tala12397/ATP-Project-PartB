package algorithms.search;

/**
 * abstract class for solving the problem
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected int num_of_nude;
    Solution sol;
    public int getNumberOfNodesEvaluated(){
        return this.num_of_nude;
    }
    public abstract String getName();
    public abstract Solution solve(ISearchable iS);
    public ASearchingAlgorithm(){
        this.sol = new Solution();
    }
}
