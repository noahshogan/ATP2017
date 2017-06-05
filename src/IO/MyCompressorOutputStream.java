package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Noah on 5/10/2017.
 */
public class MyCompressorOutputStream extends OutputStream {
    OutputStream _out;
    int counter=1;
    int last;

    int firstSix=0;
    public MyCompressorOutputStream(OutputStream out){
        _out=out;
        /*
        for (int i = 1; i < rows*cols; i++) {
            if (last==longMaze[i]){
                counter++;
            }else{
                arr.append(last);
                arr.append(",");
                arr.append(counter);
                arr.append(",");
                last=longMaze[i];
                counter=1;
            }
        }
        */

    }
    @Override
    public void write(int b) throws IOException {
        if (firstSix==6){
            last=b;
            firstSix++;
        }
        else{
            if (firstSix++<6){
                _out.write(b);
            }else{
                if (last==b){
                    counter++;
                }else{
                    _out.write(last);
                    _out.write(counter);
                    last=b;
                    counter=1;
                }
            }
        }
    }

}
