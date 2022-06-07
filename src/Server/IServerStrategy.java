package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Interface class for Strategy Server
 */
public interface IServerStrategy {
    void applyStrategy(InputStream var1, OutputStream var2);

}
