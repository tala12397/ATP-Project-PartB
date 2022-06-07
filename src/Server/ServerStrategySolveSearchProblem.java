package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
/**
 * Strategy Server class which get a maze, solve it and write the solution to Output stream
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;


    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze client_maze = (Maze) fromClient.readObject();
            Configurations configurations = Configurations.get_instance();
            String[] s = configurations.getPropValues();
            Solution maze_solution;
            ISearchingAlgorithm searcher;
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            int hash = Arrays.hashCode(client_maze.toByteArray());
            File properMaze = new File(tempDirectoryPath.concat("\\").concat(Integer.toString(hash)));
            boolean alreadySolved = properMaze.exists();
            if (alreadySolved) {
                ObjectInputStream solGetter = new ObjectInputStream(new FileInputStream(properMaze));
                maze_solution = (Solution) solGetter.readObject();
                solGetter.close();
                toClient.writeObject(maze_solution);
                toClient.flush();
                toClient.close();
                return;
            } else {

                try {
                    if (s[2].equals("DepthFirstSearch")) {
                        searcher = new DepthFirstSearch();
                    } else if (s[2].equals("BreadFirstSearch")) {
                        searcher = new BreadthFirstSearch();
                    } else {
                        searcher = new BestFirstSearch();
                    }
                } catch (Exception e) {
                    searcher = new BestFirstSearch();
                }
                FileOutputStream solwriter = new FileOutputStream(properMaze);
                ObjectOutputStream os = new ObjectOutputStream(solwriter);
                SearchableMaze searchableMaze = new SearchableMaze(client_maze);
                maze_solution = searcher.solve(searchableMaze);
                toClient.writeObject(maze_solution);
                toClient.flush();
                fromClient.close();
                toClient.close();
                os.writeObject(maze_solution);
                os.flush();
                os.close();
                solwriter.close();


            }
            } catch(Exception var6){
                var6.printStackTrace();
            }

    }
}
