package gui.ex11;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class Clock extends Frame implements Runnable{
    static int h;           //時を入れる変数を宣言
    static int m;           //分を入れる変数を宣言
    static int s;           //秒を入れる変数を宣言

    boolean a = true;

    //インスタンス化
    static Clock f = new Clock();
    static Thread th = new Thread(f);
    Calendar now = Calendar.getInstance();

    public void run(){
        while(a==true){
            h = now.getInstance().get(now.HOUR_OF_DAY); //時を代入
            m = now.getInstance().get(now.MINUTE);      //分を代入
            s = now.getInstance().get(now.SECOND);       //秒を代入
            repaint();

            try{
                th.sleep(1000);  //スリープ１秒
            }catch(InterruptedException e){
            }
        }
    }
    public void paint(Graphics g)
    {
        Font font = new Font("ＭＳ Ｐゴシック",Font.PLAIN,50);
        g.setFont(font);
        g.drawString(String.format("%02d:%02d:%02d",h,m,s),50,89);
    }

    public static void main(String args[]){
        //フレーム作成
        f.setTitle("Clock");
        f.setSize(300, 150);
        f.setVisible(true);
        f.addWindowListener(new Ada());

        th.start();   //スレッドスタート
    }

}
class Ada extends WindowAdapter
{
    public void windowClosing(WindowEvent e){   //×を押されたときの処理
        System.exit(0);
    }
}