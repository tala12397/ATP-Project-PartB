package algorithms.search;

import java.io.Serializable;

/**
 * abstract class representing a state in the problem
 */
public abstract class AState implements Serializable {
    private String state;
    protected double cost;
    private AState cameFrom;

    public AState(String s){
        this.state = s;
    }
    public abstract boolean equals(Object o);
    public abstract int getRowIndex();
    public abstract int getColumnIndex();
    public void Add_came_from(AState cF){
        this.cameFrom = cF;
    }
    public AState get_came_from(){
        return this.cameFrom;
    }

}
