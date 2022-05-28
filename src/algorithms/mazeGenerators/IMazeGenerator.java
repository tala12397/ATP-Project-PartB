package algorithms.mazeGenerators;

/**
 * interface class for generating a maze
 */
public interface IMazeGenerator {
    Maze generate(int rows, int columns);
    long measureAlgorithmTimeMillis(int rows, int columns);
}
