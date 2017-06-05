package Server;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by merav on 21/05/2017.
 */
public class Server extends java.util.Properties  {
    private FileInputStream input = null;
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
   // private static final Logger logger = LogManager.getLogger(Server.class);
    ExecutorService executor;

    public Server(int port,int listeningInterval,IServerStrategy clientHendel)  {
        this.port=port;
        this.listeningInterval=listeningInterval;
        this.serverStrategy=clientHendel;

        File file=new File("config.properties");
        Properties ourdefaultProperties=null;
        int numofThreads=2;

        if(!file.exists()){
            ourdefaultProperties=new Properties();
            try{
                input =new FileInputStream("config.properties");
                java.util.Properties ourProp= new java.util.Properties();
                ourProp.load(input);
             } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            FileReader read= null;
            try {
                // get the properties of the program if the file exist
                read = new FileReader("config.properties");
                java.util.Properties ourProp= new java.util.Properties();
                ourProp.load(read);
                String algorithmSearch=ourProp.getProperty("algoSearch");
                String algotithmBuild=ourProp.getProperty("algoBuildMaze");
                numofThreads= Integer.parseInt(ourProp.getProperty("numThread"));

                ourdefaultProperties=new Properties();
                ourdefaultProperties.setProperty("algoSearch",algorithmSearch);
                ourdefaultProperties.setProperty("algoBuildMaze",algotithmBuild);
                ourdefaultProperties.setProperty("numThread","numofThreads");
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.executor = Executors.newFixedThreadPool(numofThreads);
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }
    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            server.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket aClient = server.accept(); // blocking call
                    executor.execute( ()-> {handleClient(aClient);});
                    //new Thread(() -> {
                    //  handleClient(aClient);
                   // }).start();
                } catch (SocketTimeoutException e) {
                  //  logger.debug("SocketTimeout!");
                }
                executor.shutdown();
                server.close();
            }
            }catch (IOException e) {
      //      logger.error("IOException", e);
            }

    }

    private void handleClient(Socket aClient) {
        try {
        //    logger.debug("Client excepted!");
          //  logger.debug(String.format("Handling client with socket: %s", aClient.toString()));
            serverStrategy.serverStrategy(aClient.getInputStream(), aClient.getOutputStream());
            aClient.getInputStream().close();
            aClient.getOutputStream().close();
            aClient.close();
        } catch (IOException e) {
     //       logger.error("IOException", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("Stopping server..");
        stop = true;
    }


}
