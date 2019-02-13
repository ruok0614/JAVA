package jpl.ch14.ex05;

/**
 * saticメソッドとstatic変数を使わないで同期をとる
 */

public class Count{
    private int num;
    Count(int num){
        this.num = num;
    }

    public synchronized void subtraction(int subNum){
        this.num = this.num - subNum;
        System.out.println(subNum + ":" + this.num);
    }

}