package jpl.ch14.ex02;

import java.awt.*;

public class PrintServer implements  Runnable{
    private final PrintQueue requests = new PrintQueue();
    public PrintServer(){
        new Thread(this).start();
    }
    public void print(PrintJob job){
        requests.add(job);
    }
    public void run(){
        for(;;) {
            try {
                realPrint(requests.remove());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void realPrint(PrintJob job){

    }
}
