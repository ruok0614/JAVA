package jpl.ch01.ex12;

/**
 * 練習問題 1.12
 * ImprovedFibonacciを修正してprintlnで文字列を直接表示するのではなく，
 * Stringオブジェクトを作成して配列に入れてみてください．
 */

public class L1_12 {
    static final int MAX_INDEX = 10;
    public static void main(String[] args){

        String fibo[] = new String[MAX_INDEX];
        for( int i = 0; i < MAX_INDEX; i++) {
            fibo[i] = new String();
        }
        int lo = 1;
        int hi = 1;
        String mark;

        fibo[1] = "1: " + String.valueOf(lo);

        for (int i = 2; i < fibo.length; i++) {
            if (hi % 2 == 0){
                mark = " *";
            }else{
                mark = " ";
            }
            fibo[i] = String.valueOf(i) + ": " + String.valueOf(hi) + mark;
            hi = lo + hi;
            lo = hi - lo;
        }
        for (int i = 1; i < fibo.length; i++){
            System.out.println(fibo[i]);
        }
    }
}
