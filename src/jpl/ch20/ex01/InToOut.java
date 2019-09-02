package jpl.ch20.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InToOut {
    private void inToOut(InputStream in, byte from, byte to) throws IOException {
        OutputStream out = System.out;
        int b;
        while((b = in.read()) != -1){
            out.write( (byte)b == from ? to:(byte) b);
        }
    }

    public static void main(String args[]){
        InToOut intoout = new InToOut();
        OutputStream out;
        byte from = (byte)'a';
        byte to = (byte)'A';
        InputStream in = System.in;
        try {
            intoout.inToOut(in,from,to);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
