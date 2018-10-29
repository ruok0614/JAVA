package gui.ex13;

import java.awt.*;

public class Main {
    public static void main(String args[]){
        Frame frame = new Frame();
        ClockWindow clockWindow = new ClockWindow(frame);
        clockWindow.setVisible(true);
        clockWindow.clockStart();
    }

}
