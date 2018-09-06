package jpl.ch01.ex13;

/**
 * 演習問題 1.13
 * printlnではなくprintfを使用してImprovedFibonacciを書き直しなさい
 */

public class L1_13 {
    static final int MAX_INDEX = 9;

    public static void main(String[] args){
        int lo = 1;
        int hi = 1;
        String mark;

        System.out.printf("1: %d%n", lo);
        for (int i = 2 ; i <= MAX_INDEX ; i++){
            if (hi % 2 == 0){
                mark = " *";
            }else{
                mark = " ";
            }
            System.out.printf("%d: %d %s%n" ,i,hi,mark);
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}
