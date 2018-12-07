package gui.ex23;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class PopUpMenue extends JPopupMenu implements ActionListener {
    Map colorMap;
    ChangeColor changeColor;
    ChangeFontSize changeFontSize;

    JRadioButtonMenuItem[] fontColors;
    JRadioButtonMenuItem[] fontSizeList;
    PopUpMenue(){


        JMenu color = new JMenu("文字色の変更");
        JMenu fontSize = new JMenu("フォントサイズの変更");
        Exit exit = new Exit();
        changeColor = new ChangeColor();
        changeFontSize = new ChangeFontSize();
        fontColors = new JRadioButtonMenuItem[changeColor.colorMap.size()];
        // カラーマップのキーのみをリストに入れる
        ArrayList<String> n = new ArrayList<String>(changeColor.colorMap.keySet());

        fontSizeList = new JRadioButtonMenuItem[changeFontSize.getLength()];

        JMenuItem propertyMenuItem = new JMenuItem("終了");

        ButtonGroup fontColorsGroup = new ButtonGroup();
        ButtonGroup fontSizeGroup = new ButtonGroup();

        for(int i = 0; i < fontSizeList.length; i++){
            fontSizeList[i] = new JRadioButtonMenuItem( String.valueOf( changeFontSize.getSize(i) ) );
            fontColorsGroup.add(fontSizeList[i]);
            fontSize.add(fontSizeList[i]);
            fontSizeList[i].addActionListener(changeFontSize);
        }


        for(int i = 0; i < fontColors.length; i++){
            fontColors[i] = new JRadioButtonMenuItem(n.get(i));
            fontSizeGroup.add(fontColors[i]);
            color.add(fontColors[i]);
            fontColors[i].addActionListener(changeColor);
        }
        // デフォルトの塗りつぶす
        fontColors[3].setSelected(true);
        fontSizeList[4].setSelected(true);

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

    class ChangeFontSize extends AbstractAction{
        int size = 50;
        int[] sizes = {10,20,30,40,50,60,70,80,90,100};

        public int getSize(int index) {
            return sizes[index];
        }
        public int getLength(){
            return sizes.length;
        }
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < fontSizeList.length; i++) {
                if (fontSizeList[i].isSelected()) {
                    size = Integer.parseInt(fontSizeList[i].getText());
                }
            }
        }
    }

    class ChangeColor extends AbstractAction{
        Map colorMap;
        {
            colorMap = new HashMap<String, java.awt.Color>();
            colorMap.put("YELLOW", Color.YELLOW);
            colorMap.put("GREEN", Color.GREEN);
            colorMap.put("WHITE", Color.WHITE);
            colorMap.put("BLACK",Color.BLACK);
            colorMap.put("RED",Color.RED );
            colorMap.put("CYAN",Color.CYAN );
            colorMap.put("MAGENTA",Color.MAGENTA);
            colorMap.put("ORANGE",Color.ORANGE);
            colorMap.put("pink",Color.pink);
            colorMap.put("DARK_GRAY",Color.DARK_GRAY);
        }

        Color color = Color.BLACK;

        public void actionPerformed(ActionEvent e){
            for (int i = 0 ; i < fontColors.length; i++){
                if (fontColors[i].isSelected()){
                    color = (Color)colorMap.get(fontColors[i].getText());
                    System.out.println(color);
                }
            }

        }
    }
}

