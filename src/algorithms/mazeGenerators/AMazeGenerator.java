package algorithms.mazeGenerators;

/**
 * Abstract class for generation a maze
 */
public abstract class AMazeGenerator implements IMazeGenerator {
    abstract public Maze generate(int rows, int columns);

    /**
     * calculate the time for generate a new maze
     * @param rows  the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @return the time for generating a maze in ms
     */
    public long measureAlgorithmTimeMillis(int rows, int columns){
        long before = System.currentTimeMillis();
        generate(rows, columns);
        long after = System.currentTimeMillis();
        return after - before;

    }

}
