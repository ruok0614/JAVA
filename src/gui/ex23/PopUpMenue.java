package gui.ex23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class PopUpMenue extends JPopupMenu implements ActionListener {
    Map colorMap;

    JRadioButtonMenuItem[] fontColors;
    JRadioButtonMenuItem[] fontSizeList;
    PopUpMenue(){


        JMenu color = new JMenu("文字色の変更");
        JMenu fontSize = new JMenu("フォントサイズの変更");
        Exit exit = new Exit();
        fontColors = new JRadioButtonMenuItem[5];
        fontColors[0] = new JRadioButtonMenuItem("Yellow");
        fontColors[1] = new JRadioButtonMenuItem("Green");
        fontColors[2] = new JRadioButtonMenuItem("White");
        fontColors[3] = new JRadioButtonMenuItem("Black",true);
        fontColors[4] = new JRadioButtonMenuItem("Red");

        fontSizeList = new JRadioButtonMenuItem[5];
        fontSizeList[0] = new JRadioButtonMenuItem("20");
        fontSizeList[1] = new JRadioButtonMenuItem("30");
        fontSizeList[2] = new JRadioButtonMenuItem("40");
        fontSizeList[3] = new JRadioButtonMenuItem("50",true);
        fontSizeList[4] = new JRadioButtonMenuItem("60");


        JMenuItem propertyMenuItem = new JMenuItem("終了");

        ButtonGroup fontColorsGroup = new ButtonGroup();
        ButtonGroup fontSizeGroup = new ButtonGroup();

        for(int i = 0; i < fontSizeList.length; i++){
            fontColorsGroup.add(fontSizeList[i]);
            fontSize.add(fontSizeList[i]);
            fontSizeList[i].addActionListener(this);
        }

        for(int i = 0; i < fontColors.length; i++){
            fontSizeGroup.add(fontColors[i]);
            color.add(fontColors[i]);
            fontColors[i].addActionListener(this);
        }

        add(fontSize);
        add(color);
        add(propertyMenuItem);

        propertyMenuItem.addActionListener(exit);

    }
    public void actionPerformed(ActionEvent e) {

    }
    class Exit extends AbstractAction {
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    class ChangeColor extends AbstractAction{

        String color;

        public void actionPerformed(ActionEvent e){
//            for (int i = 0 ; i < fontColors.length; i++){
//                if (fontColors[i].isSelected()){
//                    color = fontColors[i].getText();
//                    System.out.println(color);
//                }
//            }

        }
    }
}

