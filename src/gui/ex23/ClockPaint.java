package gui.ex23;

import javax.swing.*;
import java.awt.*;

public class ClockPaint extends JPanel {
    gui.ex23.Clock clock;
    Thread th;
    ClockPaint(){
        clock = new Clock();
        th = new Thread(clock);
        th.start();
    }
//    public void paintComponent(Graphics g){
//    }
}
