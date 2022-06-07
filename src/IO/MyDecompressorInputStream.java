package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
/**
 * class which decompresses a maze according to my algorithm
 */
public class MyDecompressorInputStream extends InputStream  {
    InputStream in;

    public MyDecompressorInputStream(InputStream inpt){
        this.in = inpt;
    }
    public int read()throws IOException{
        return 0;
    }

    /**
     * decompress func
     * @param new_byte is the decompress array
     * @return -
     * @throws IOException
     */
    public int read(byte [] new_byte) throws IOException {
        byte[] bytes = in.readAllBytes();
        for (int i = 0; i < 12; i++) {
            new_byte[i] = bytes[i];
        }
        String s = "";
        for(int i = 12;i<bytes.length;i++){
            s+=Integer.toString(bytes[i]);
        }
        BigInteger num = new BigInteger(s);
        s = num.toString(2);
        String sub_s = "";
        for(int i=0;i<s.length();i++){
            sub_s = s.substring(i,i+1);
            new_byte[i+12] = (byte)Integer.parseInt(sub_s);
        }
        return 0;
    }
}
