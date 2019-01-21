package jpl.ch14.ex06;

/**
 * 15秒感覚でメッセージを表示する別のスレッドを持ち，
 * 実行開始時間からの経過時間を表示するプログラムを作成しなさい．
 * メッセージ表示スレッドは，時間表示スレッドから1秒経過するごとに通知されるようにしなさい．
 * 時間表示スレッドを修正することなく，7秒感覚で異なるメッセージを表示する別のスレッドを追加しなさい
 */

public class Main {
    public static void main(String args[]){
        Clock clock = new Clock();
        Clock clock2 = new Clock();
        Runnable fif = clock::fifteenMessage;
        Runnable seven = clock2::sevenWait;
        Thread th1 = new Thread(seven);
        th1.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread th2 = new Thread(fif);
        th2.start();




    }
}
