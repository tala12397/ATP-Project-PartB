package IO;

import java.io.IOException;
import java.io.InputStream;
/**
 * class which decompresses a maze according to hw algorithm
 */
public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    public SimpleDecompressorInputStream(InputStream inpt){
        this.in = inpt;
    }
    public int read()throws IOException{
        return 0;
    }
    @Override
    public int read(byte [] new_byte) throws IOException {
       byte [] bytes = in.readAllBytes();
       for(int i=0;i<12;i++) {
           new_byte[i] = bytes[i];
       }
       int next_push = 12;
       int flag = 0;
       for(int i=12;i<bytes.length;i++){
           int count =(int)bytes[i]& 0xff;
           if(flag == 0) {
                   flag = 1;
               while (count > 0) {
                   new_byte[next_push] = 0;
                   next_push++;
                   count--;
               }
           }
           else{
               flag = 0;
               while (count > 0) {
                   new_byte[next_push] = 1;
                   next_push++;
                   count--;
               }
           }
       }
       return 0;
    }

}
