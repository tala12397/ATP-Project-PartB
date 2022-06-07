package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *  interface class for class representing a Client Strategy
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
