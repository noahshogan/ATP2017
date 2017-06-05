package Client;

/**
 * Created by merav on 21/05/2017.
 */
import java.io.*;
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
