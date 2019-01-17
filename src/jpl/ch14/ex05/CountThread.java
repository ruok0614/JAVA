package jpl.ch14.ex05;

public class CountThread implements Runnable {
    Thread th;
    Count c;
    int subNum;

    CountThread(Count c,int subNum){
        this.c = c;
        this.subNum = subNum;
        th = new Thread(this);
        th.start();
    }

    public void run(){
        for(int i = 0; i<12;i++){
            c.subtraction(subNum);
        }

    }

    public static void main(String args[]){
        Count count = new Count(74000);
        CountThread ct1 = new CountThread(count,3);
        CountThread ct2 = new CountThread(count,7);

    }
}
