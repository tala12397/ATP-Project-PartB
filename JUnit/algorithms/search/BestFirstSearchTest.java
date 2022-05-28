package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {


    @Test
    void solve() {
        MyMazeGenerator my_maze = new MyMazeGenerator();
        Maze empty = my_maze.generate(0,0);
        SearchableMaze iSearchable_null = new SearchableMaze(null);
        SearchableMaze iSearchable_empty = new SearchableMaze(empty);
        BestFirstSearch best_fs = new BestFirstSearch();
        Solution solution_empty = best_fs.solve(iSearchable_empty);
        Assertions.assertNotEquals(null, best_fs.solve(iSearchable_null));
        Assertions.assertNotEquals(null,solution_empty);
        SearchableMaze iSearchable_normal = new SearchableMaze(new Maze(1000,1000));
        Solution normal_sol = best_fs.solve(iSearchable_normal);
        Assertions.assertNotEquals(null, normal_sol);

    }
}