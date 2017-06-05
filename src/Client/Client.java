package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by merav on 21/05/2017.
 */
public class Client {
    private InetAddress ip;
    private int port;
    private IClientStrategy clientStrategy;

    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.ip = IP;
        this.port = port;
        this.clientStrategy = clientStrategy;
    }

    public void communicateWithServer(){
        try {
            Socket theServer = new Socket(ip, port);
            System.out.println("Connected to server!");
            clientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
