package algorithms.search;

import java.util.Objects;

/**
 * a class who representing a specific state for solving a maze
 */
public class MazeState extends AState{
    private int row;
    private int col;

    /**
     * a constructor
     * @param r the row index
     * @param c the columns index
     * @param s the string value of the state
     */
    public MazeState(int r, int c, String s){
        super(s);
        this.row = r;
        this.col = c;
    }

    /**
     * a constructor
     * @param r the row index
     * @param c the columns index
     * @param cost the cost of a step to the cell.
     */
    public MazeState(int r, int c, int cost){
        super("");
        this.row = r;
        this.col = c;
        this.cost = cost;
    }

    /**
     * equals between who MazeState objects.
     * @param o the parameter to compare
     * @return true if it the values are equals. else false.
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazeState = (MazeState) o;
        return row == mazeState.row && col == mazeState.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
    public int getRowIndex(){
        return this.row;
    }
    public int getColumnIndex(){
        return this.col;
    }
    @Override
    public String toString(){
        return "{" + this.row + "," + this.col + "}";
    }
}
