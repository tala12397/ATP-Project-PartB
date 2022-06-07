package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
/**
 * Strategy Server class which generate a maze, compress it and write it to Output stream
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;


    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] client_data = (int[])fromClient.readObject();
            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(client_data[0], client_data[1]);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Configurations configurations = Configurations.get_instance();
            String []s = configurations.getPropValues();
            OutputStream out;
            if(s[1].equals("MyCompressorOutputStream")){
                out = new MyCompressorOutputStream(byteArrayOutputStream);
            }
            else{
                out = new SimpleCompressorOutputStream(byteArrayOutputStream);
            }
            byte [] maze_bytes = maze.toByteArray();
            out.write(maze_bytes);
            out.flush();
            toClient.writeObject(byteArrayOutputStream.toByteArray());
            toClient.flush();
            byteArrayOutputStream.close();
            out.close();
            fromClient.close();
            toClient.close();


        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

}
