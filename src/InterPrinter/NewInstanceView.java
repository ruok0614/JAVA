package InterPrinter;

import javax.swing.*;


public class NewInstanceView extends JFrame{
    NewInstanceView(){
        setSize(150, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("NewInstance");
        //位置を適切にしてくれるやつ
        setLocationByPlatform(true);
    }

}
