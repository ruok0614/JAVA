package gui.ex23;

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        JFrame frame = new JFrame();
        ClockWindow clockFrame = new ClockWindow(frame);
        clockFrame.setVisible(true);
    }
}