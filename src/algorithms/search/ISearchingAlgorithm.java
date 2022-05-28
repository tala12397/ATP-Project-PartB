package algorithms.search;

/**
 * an interface for solving the problem.
 */
public interface ISearchingAlgorithm {

   int getNumberOfNodesEvaluated();
   String getName();
   Solution solve(ISearchable iS);


}
