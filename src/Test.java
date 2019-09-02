import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.*;

public class Test{

    public static void main(String[] args){

        long[] longArray = new long[1000000];
        for(long i=0; i < longArray.length;i++){
            longArray[(int)i] = i + 100000;
        }
        LocalDateTime start = LocalDateTime.now();

        long sum = 0L;
        for (long l:longArray ) {
            sum += l;
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(sum);
        System.out.println(String.format("%s",Duration.between(start,end).toMillis()));


//        Long[] longArray = new Long[1000000];
//        for(long i=0; i < longArray.length;i++){
//            longArray[(int)i] = i + 100000;
//        }
//        LocalDateTime start = LocalDateTime.now();
//
//        Long sum = 0L;
//        for (Long l:longArray ) {
//            sum += l;
//        }
//        LocalDateTime end = LocalDateTime.now();
//        System.out.println(sum);
//        System.out.println(String.format("%s",Duration.between(start,end).toMillis()));

    }
}