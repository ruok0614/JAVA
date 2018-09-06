package jpl.ch01.ex03;

import jpl.ch01.ex01.L1_1;

public class L1_3 {
    //値が50未満のフィボナッチ数列を表示する

    // staticはpublic の後に書く
    public static int fibonacci(int n){
        if (n <= 0){
            return 0;
        } else if (n == 1){
            return 1;
        }else {
            return fibonacci(n -2) + fibonacci(n -1);
        }
    }

    public static void main(String[] args) {

        L1_3 fibo = new L1_3();
        int cnt = 0;

        while(fibo.fibonacci(cnt) < 50) {
            System.out.println(cnt + ":"  + fibo.fibonacci(cnt));
            cnt++;
        }
    }

}

