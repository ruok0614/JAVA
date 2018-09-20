package gui.ex12;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Ada extends WindowAdapter
{
    public void windowClosing(WindowEvent e){   //×を押されたときの処理
        System.exit(0);
    }
}