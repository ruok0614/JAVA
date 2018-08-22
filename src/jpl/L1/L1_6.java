package jpl.L1;

/**
 * 練習問題1.6
 * 　練習問題1.3で作成したプログラムを修正して，
 * タイトルに対して名前付文字列定数を使用するように修正しなさい
 */

public class L1_6 {
    //値が50未満のフィボナッチ数列を表示する
    static final String TITLE = "50未満のフィボナッチ数列を出力";
    public static void main(String[] args) {
        int lo = 1,
            hi = 1,
            cnt = 0;
        System.out.println(TITLE);
        System.out.println(cnt + ":"  + hi);
        while(hi < 50) {
            System.out.println(cnt + ":"  + hi);
            hi = hi + lo;
            lo = hi - lo;
            cnt++;
        }

    }

}

