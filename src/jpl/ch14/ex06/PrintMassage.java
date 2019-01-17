package jpl.ch14.ex06;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrintMassage implements Runnable{

    public synchronized void run(){
        while(true) {
            try {
                System.out.println("wait!");
                wait();
                System.out.println("7秒経過");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
