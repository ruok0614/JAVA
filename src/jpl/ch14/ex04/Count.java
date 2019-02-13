package jpl.ch14.ex04;

/**
 * saticメソッドとstatic変数を使う
 */

public class Count {
    static int num ;
    Count(int num){
        this.num = num;
    }
    public static void add(int addNum){
        num += addNum;
        System.out.println(num);
    }

}

class CountThread implements Runnable{
    Thread th;
    Count count;
    int loopCount = 5;
    CountThread(){
        th = new Thread();
        count = new Count(5);
        th.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < loopCount; i++){
            count.add(2);
        }

    }

    public static void main(String args[]){
        CountThread countThread = new CountThread();
        Thread th = new Thread(countThread);
        Thread th2 = new Thread(countThread);

        th.start();
        th2.start();

    }
}