package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import  algorithms.search.*;
import algorithms.mazeGenerators.*;
/**
 * Created by pollme on 6/4/2017.
 */
public class Properties extends java.util.Properties{


    public static java.util.Properties ourProperties=new java.util.Properties ();
    OutputStream out;

    public Properties() {
        try {

            out = new FileOutputStream("config.properties");
            ourProperties.setProperty("algoSearch", "DepthFirstSearch");
            ourProperties.setProperty("algoBuildMaze", "MyMazeGenerator");
            ourProperties.setProperty("numThread", "2");
            ourProperties.store(out, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public int getNumThreads(){
        return Integer.parseInt(ourProperties.getProperty("numThread"));
    }

    public static ISearchingAlgorithm getalgoSearch(){
        if (ourProperties.getProperty("algoSearch").equals("DepthFirstSearch")){
            return new DepthFirstSearch();
        }else if(ourProperties.getProperty("algoSearch").equals("BreadthFirstSearch"))
            return new BreadthFirstSearch();
        else if(ourProperties.getProperty("algoSearch").equals("BestFirstSearch"))
            return new BestFirstSearch();

        return new DepthFirstSearch();
    }

    public static IMazeGenerator getMazegenerator() {
         if (ourProperties.getProperty("algoBuildMaze").equals("MyMazeGenerator"))
            return new MyMazeGenerator();
        else if (ourProperties.getProperty("algoBuildMaze").equals("SimpleMazeGenerator"))
            return new SimpleMazeGenerator();

        return new MyMazeGenerator();
    }



}
