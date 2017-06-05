package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by merav on 21/05/2017.
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws ClassNotFoundException;
}
