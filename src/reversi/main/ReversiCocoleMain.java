package reversi.main;

import reversi.gui.ReversiFrame;
import reversi.input.ConsoleInputHandler;
import reversi.input.GuiInputHundler;
import reversi.system.Flow;

import java.awt.*;
import java.awt.event.MouseListener;

public class ReversiCocoleMain{;

    public static void main(String args[]){
//        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
//        Flow flow = new Flow(8,8,consoleInputHandler);
//        flow.start();
//        ReversiFrame reversiFrame = new ReversiFrame(8,8);
//        reversiFrame.setVisible(true);
//
//        GuiInputHundler guiInputHundler = new GuiInputHundler(8,8,reversiFrame);
//        guiInputHundler.start();

        ReversiFrame frame = new ReversiFrame(8,8);
        frame.setVisible(true);

    }
}