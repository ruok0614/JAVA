package gui.ex13;

import java.awt.*;
import java.awt.event.MouseListener;

public class PopUpMenue extends PopupMenu {
    PopUpMenue(){
        Menu color = new Menu("color");
        MenuItem red = new MenuItem("red");
        color.add(red);
        add(color);
    }
}
