package jpl.ch01.ex09;

/**
 * 練習問題 1.9
 *  Fibonacciのアプリケーションを修正して，数列を配列に保存して，
 *  最後に値のリストを表示するようにしなさい
 *
 */
public class L1_9 {
    //値が50未満のフィボナッチ数列を表示する
    public static void main(String[] args) {
        int[] fibo = new int[10];

        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i < fibo.length; i++) {
            fibo[i] = fibo[i-2] + fibo[i-1];
        }
        for (int i = 0; i < fibo.length; i++){
            System.out.println(i + ":"  + fibo[i]);
        }

    }
}
