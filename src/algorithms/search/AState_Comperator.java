package algorithms.search;

import java.util.Comparator;

/**
 * comperator class for the priority queue.
 */
public class AState_Comperator implements Comparator<AState> {
    /**
     * compare function
     * @param a is the first parameter to compare
     * @param b is the second parameter to compare
     * @return 1 if the first is bigger, -1 if the second is bigger and 0 if equals.
     */
    public int compare(AState a, AState b){
        if(a.cost < b.cost)
            return -1;
        if(a.cost > b.cost)
            return 1;
        return 0;
    }
}
