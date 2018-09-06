package jpl.ch01.text;

public class Fibonacci {
    //値が50未満のフィボナッチ数列を表示する
    public static void main(String[] args) {
        int lo = 1,
            hi = 1,
            cnt = 0;
        System.out.println(cnt + ":"  + hi);
        while(hi < 50) {
            System.out.println(cnt + ":"  + hi);
            hi = hi + lo;
            lo = hi - lo;
            cnt++;
        }

    }

}

