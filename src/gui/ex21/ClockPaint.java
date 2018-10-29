package gui.ex21;

import javax.swing.*;
import java.awt.*;

public class ClockPaint extends JPanel {
    Clock clock;
    Thread th;
    ClockPaint(){
        clock = new Clock();
        th = new Thread(clock);
        th.start();
    }
    public void paintComponent(Graphics g){
        //拝啓を描く
        super.paintComponent(g);
        Font font = new Font("ＭＳ Ｐゴシック",Font.PLAIN,50);
        g.setColor(Color.BLACK);
        setFont(font);
        g.drawString(String.format("%02d:%02d:%02d",clock.getH(),clock.getM(),clock.getS()),50,80);
    }
}
