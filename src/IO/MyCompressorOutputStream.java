package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream os){
        this.out = os;
    }
    public void write(int b) throws IOException {
        out.write(b);
    }
    public void write(byte[] b) throws IOException {
        for(int i=0;i<b.length;i++){
            out.write(b[i]);
        }
    }


}
