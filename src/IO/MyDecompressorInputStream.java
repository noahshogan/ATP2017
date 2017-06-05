package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Noah on 5/10/2017.
 */
public class MyDecompressorInputStream extends InputStream{
    InputStream _in;
    int firstSix=0;
    int last;

    int counter;
    public MyDecompressorInputStream(InputStream in){
        _in=in;
    }
    @Override
    public int read() throws IOException {
        if (firstSix==6){
            last=_in.read();
            counter=(_in.read());
        }
        if (firstSix++<6){
            return _in.read();
        }else{
            if (!(counter-->0)){
                last=_in.read();
                counter=(_in.read()-1);
            }

        }
        return last;
    }
}
