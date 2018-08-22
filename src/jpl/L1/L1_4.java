package jpl.L1;

/**
練習問題1.4
　何らかの数列を出力するプログラムを作成せよ，例えば平方表など
 */

public class L1_4 {
    static final int MAX_O_NUM = 50; /* 原数の最大値
                                        staticをつけるのは定数をクラスのインスタンスに付属させたくないため */
    public static void main(String[] args){
        int square = 0,
            original_number = 0;

        while(original_number <= MAX_O_NUM){
            square = original_number * original_number;
            System.out.println("原数:" + original_number + "\t平方:" + square);
            original_number ++;
        }
    }
}
