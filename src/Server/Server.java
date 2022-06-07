package Server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class
 */
public class Server{
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        Configurations configurations = Configurations.get_instance();
        String[] s = new String[3];
        try {
           s = configurations.getPropValues();
        }
        catch (IOException e){
            System.out.println("problem");
        }
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(s[0]));
    }

    /**
     * function which execute run function with thread
     */
    public void start() {
        Thread p = new Thread(()->run());
        p.start();

    }
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            serverSocket.setSoTimeout(this.listeningIntervalMS);
            while(!this.stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    this.threadPool.submit(() -> {
                        this.handleClient(clientSocket);
                    });
                } catch (SocketTimeoutException var3) {

                }
            }

            serverSocket.close();
            this.threadPool.shutdownNow();
        } catch (IOException var4) {

        }
    }
    private void handleClient(Socket clientSocket) {
        try {
            this.strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (IOException var3) {
        }

    }
    public void stop() {
        this.stop = true;
    }




}
