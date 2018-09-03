package gui.ex11;

import java.awt.*;

public class ClockFrame extends Frame {

    Clock clock;
    Thread th;

    ClockFrame(){
        clock = new Clock();
        setTitle("Clock");
        setSize(300, 150);
        // ↓はメインで呼ぶ
        setVisible(true);
        addWindowListener(new Ada());
        clock.th.start();

    }

    public void paint(Graphics g)
    {
        Font font = new Font("ＭＳ Ｐゴシック",Font.PLAIN,50);
        g.setFont(font);
        g.drawString(String.format("%02d:%02d:%02d",clock.h,clock.m,clock.s),50,89);
    }

    void clockRun(){
        boolean a = true;
        while (a==true){
            repaint();
            try{
                clock.th.sleep(100);  //スリープ１秒
            }catch(InterruptedException e){

            }
        }
    }

}
