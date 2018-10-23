package reversi.main;

import reversi.input.GuiInputHundler;
import reversi.input.ReversiFrame;
import reversi.system.Flow;

import java.awt.event.MouseListener;

public class ReversiCocoleMain{;

    public static void main(String args[]){
        ReversiFrame reversiFrame = new ReversiFrame();
        reversiFrame.setVisible(true);

        GuiInputHundler guiInputHundler = new GuiInputHundler(8,8,reversiFrame);
        guiInputHundler.start();

    }
}