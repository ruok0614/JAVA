package jpl.ch14.ex06;

import java.util.Calendar;

import static java.lang.Thread.sleep;

class Clock{
    private int h;           //時を入れる変数を宣言
    private int m;           //分を入れる変数を宣言
    private int s;           //秒を入れる変数を宣言
    long startTime;
    long elapsedTime;

    Clock(){
        // 処理前の時刻を取得
        startTime = System.currentTimeMillis();
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

    public void noti(){

    }

    public synchronized void fifteenMessage(){

        while(true){

            long tempTime = System.currentTimeMillis();
            elapsedTime = (tempTime - startTime)/1000;
            System.out.println(elapsedTime);
            if (elapsedTime%15 == 0 && elapsedTime!=0 ){
                System.out.println("15秒経過");
            }
            try{
                sleep(1000);//スリープ１秒
                notifyAll();
            }catch(InterruptedException e){
            }
        }
    }

    public synchronized void sevenWait(){
        int s = 0;
        while(true) {
            try {
                Thread.sleep(100);
                System.out.println("wait!");
                wait();
                s++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(s % 7 == 0) {
                System.out.printf("%d秒経過\n",s);
            }
        }
    }

}
