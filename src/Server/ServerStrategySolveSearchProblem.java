package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;
/**
 * Created by merav on 21/05/2017.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
   @Override
   public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
      //the server gets a maze object, solve the maze and sends Solution object to the client
      try {
         String tempDirectoryPath = System.getProperty("java.io.tmpdir");
         ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
         ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
         toClient.flush();

         Maze maze = (Maze)fromClient.readObject();

         byte[] toByte = maze.toByteArray();
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(toByte.length);
         new MyCompressorOutputStream(byteArrayOutputStream).write(toByte);
         byte[] compressMaze = byteArrayOutputStream.toByteArray();
         File myFile = new File(tempDirectoryPath,((Integer )new String(compressMaze).hashCode()).toString() +".maze");
         Solution solution;
         if(!myFile.exists()){
            solution = Properties.getalgoSearch().solve(new SearchableMaze(maze));
            myFile.createNewFile();
            ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(myFile));
            fileOut.writeObject(solution);
            fileOut.close();
            fileOut.flush();
         }
         else{
            solution = (Solution) new ObjectInputStream(new FileInputStream(myFile)).readObject();
         }
         toClient.writeObject(solution);
         toClient.flush();
      }

      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
