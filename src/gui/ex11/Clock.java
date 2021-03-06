package gui.ex11;

import java.util.Calendar;

class Clock implements Runnable{
    int h;           //時を入れる変数を宣言
    int m;           //分を入れる変数を宣言
    int s;           //秒を入れる変数を宣言

    boolean a = true;

    //インスタンス化
    Thread th;
    Clock(){
        // Threadを呼び出し、Runnnableで
        th = new Thread(this);
    }



    public void run(){
        while(a==true){
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

}
