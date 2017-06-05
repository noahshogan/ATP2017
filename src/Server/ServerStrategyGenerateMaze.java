package Server;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.IMazeGenerator;
import java.io.*;
import IO.MyCompressorOutputStream;


/**
 * Created by merav on 21/05/2017.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy{

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient){

        try{
            //get array of 2 , the server creates maze from it compress it and send back to the client byte[]
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            //send the maze to compressor
            int[] mazetoBuild=(int[])fromClient.readObject();
            byte[] MazeTOByte=Properties.getMazegenerator().generate(mazetoBuild[0],mazetoBuild[1]).toByteArray();
            ByteArrayOutputStream byteArray=new ByteArrayOutputStream(MazeTOByte.length);
            new MyCompressorOutputStream(byteArray).write(MazeTOByte);
            toClient.writeObject(byteArray.toByteArray());
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
