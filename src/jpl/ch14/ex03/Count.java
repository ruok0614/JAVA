package jpl.ch14.ex03;

public class Count {
    int num ;
    Count(int num){
        this.num = num;
    }
    public void add(){
        this.num += this.num;
        System.out.println(num);
    }

}

class CountThread implements Runnable{
    Thread th;
    Count count;
    CountThread(){
        th = new Thread();
        count = new Count(1);
        th.start();
    }

    @Override
    public void run() {
        count.add();
    }

    public static void main(String args[]){
        CountThread countThread = new CountThread();
    }
}