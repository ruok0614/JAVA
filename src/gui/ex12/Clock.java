package gui.ex12;

import java.util.Calendar;
import java.util.Objects;

class Clock implements Runnable{
    private int h;           //時を入れる変数を宣言
    private int m;           //分を入れる変数を宣言
    private int s;           //秒を入れる変数を宣言



    //インスタンス化
    private Thread th;
    Clock(){
        // Threadを呼び出し、Runnnableで
        th = new Thread(this);
    }

    public void start(){
        th.start();
    }

    public void run(){
        while(true){
            // getInstanceはstaticメソッド。なのでクラス名で指定
            h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); //時を代入
            m = Calendar.getInstance().get(Calendar.MINUTE);      //分を代入
            s = Calendar.getInstance().get(Calendar.SECOND);       //秒を代入
            try{
                th.sleep(100);  //スリープ１秒
            }catch(InterruptedException e){
            }
        }
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }


}
