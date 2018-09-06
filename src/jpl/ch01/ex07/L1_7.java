package jpl.ch01.ex07;

public class L1_7 {
    static final int MAX_INDEX = 9;

    /**
     * 練習問題1.7
     * 　iが逆順に値が減るようにImproveFibonacciのループを書き直しなさい
     */
    public static void main(String[] args){
        int lo = 1;
        int hi = 1;
        String mark;

        System.out.println(MAX_INDEX + ": " + lo);
        for (int i = MAX_INDEX - 1 ; 0 < i ; i--){
            if (hi % 2 == 0){
                mark = " *";
            }else{
                mark = " ";
            }
            System.out.println(i + ": " + hi + mark);
            hi = lo + hi;
            lo = hi - lo;
        }
    }
}
