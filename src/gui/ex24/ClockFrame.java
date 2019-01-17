package gui.ex24;

import javax.swing.*;
import java.awt.*;

public class ClockFrame extends JFrame {
    ClockPaint clockPaint;
    ClockFrame() {
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Clock");
        //位置を適切にしてくれるやつ
        setLocationByPlatform(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("C:Users/qazws/IdeaProjects/java/src/gui/ex21/icon.jpg");
        setIconImage(icon);

        clockPaint = new ClockPaint();
        add(clockPaint);
        //clockPaint.repaint();
    }


}
