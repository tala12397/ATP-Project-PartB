package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;


    public MyCompressorOutputStream(OutputStream os){
        this.out = os;

    }
    public void write(int b) throws IOException {

    }
    public void write(byte[] bytes) throws IOException{
        int index = 12;
        String s = "";
        String new_s = "";
        for(int i=0;i<12;i++){
            if(i%2==0)
                out.write(bytes[i]);
            else
                out.write(bytes[i]);
        }
        for(int i=12;i<bytes.length;i++){
            s+=Integer.toString(bytes[i]);
        }
        BigInteger BigInt = new BigInteger(s);

        String n_S = BigInt.toString();
        int count = 0;
        while(n_S.length()>count){
            new_s = n_S.substring(count,count+1);
            out.write(Integer.parseInt(new_s));
            count++;
        }

    }



}
