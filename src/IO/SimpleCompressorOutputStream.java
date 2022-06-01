package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    public SimpleCompressorOutputStream(OutputStream os){
        this.out = os;
    }
    public void write (int b) throws IOException{

    }
    public void write(byte[] bytes) throws IOException {
        int count0 = 0;
        int count1 = 0;
        int index = 12;
        for(int i=0;i<12;i++){
            if(i%2==0)
                out.write(bytes[i]);
            else
                out.write(bytes[i]);
        }
        if(bytes[index] == 0){
            index++;
            count0++;
            while(bytes[index]==0){
                index++;
                count0++;
            }

        }
        if(count0<=255)
            out.write(count0);
        else{
            while(count0>255){
                out.write(255);
                out.write(0);
                count0 -= 255;
            }
            out.write(count0);
        }
        while(index< bytes.length){
            count0 = 0;
            count1 = 0;
            if(bytes[index] == 0){
                index++;
                count0++;
                while(index<bytes.length && bytes[index]==0){
                    index++;
                    count0++;
                }
                if(count0<=255)
                    out.write(count0);
                else{
                    while(count0>255){
                        out.write(255);
                        out.write(0);
                        count0 -= 255;
                    }
                    out.write(count0);
                }

            }
            else{
                index++;
                count1++;
                while(index< bytes.length && bytes[index]==1){
                    index++;
                    count1++;
                }
                if(count1<=255)
                    out.write(count1);
                else{
                    while(count1>255){
                        out.write(255);
                        out.write(0);
                        count1 -= 255;
                    }
                    out.write(count1);
                }
            }
        }

    }

}
