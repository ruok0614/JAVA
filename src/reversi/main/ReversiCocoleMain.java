package reversi.main;

import reversi.gui.ReversiFrame;
import reversi.input.GuiInputHundler;
import reversi.system.Flow;

import java.awt.*;
import java.awt.event.MouseListener;

public class ReversiCocoleMain{;

    public static void main(String args[]){
//        ReversiFrame reversiFrame = new ReversiFrame(8,8);
//        reversiFrame.setVisible(true);
//
//        GuiInputHundler guiInputHundler = new GuiInputHundler(8,8,reversiFrame);
//        guiInputHundler.start();

        ReversiFrame frame = new ReversiFrame(8,8);
        frame.setVisible(true);

    }
}