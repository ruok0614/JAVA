package gui.ex12;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockMenu implements ActionListener {
    MenuBar menuBar;
    Menu menuPrp;
    MenuItem menuFontSize;
    MenuItem menuFont;
    MenuItem menuFontColor;
    MenuItem menuBackColor;
    ClockFrame clockFrame;

    ClockMenu(ClockFrame clockFrame){
        this.clockFrame = clockFrame;
        menuBar = new MenuBar();
        menuPrp = new Menu("プロパティ");
        menuPrp.addActionListener(this);
        menuBar.add(menuPrp);
        menuFontSize = new MenuItem("文字サイズ");
        menuPrp.add(menuFontSize);
        menuFontSize.addActionListener(this);
        menuFont = new MenuItem("フォント指定");
        menuPrp.add(menuFont);
        menuFont.addActionListener(this);
        menuFontColor = new MenuItem("文字色");
        menuPrp.add(menuFontColor);
        menuFontColor.addActionListener(this);
        menuBackColor = new MenuItem("背景色");
        menuPrp.add(menuBackColor);
        menuBackColor.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuFontColor) {
            //clockFrame.fontColor = Color.blue;
            MyDialogListColor dlg = new MyDialogListColor(clockFrame);
            dlg.show();
        }else if(e.getSource() == menuFont){
            MyDialogListFont dlg = new MyDialogListFont(clockFrame);
            dlg.show();
        }else if(e.getSource() == menuFontSize){
            MyDialogFontSize dlg = new MyDialogFontSize(clockFrame);
            dlg.show();
        }else if(e.getSource() == menuBackColor){
            MyDialogListBackColor dlg = new MyDialogListBackColor(clockFrame);
            dlg.show();
        }
    }
    class MyDialogListColor extends Dialog implements ActionListener {
        List list;
        String[] colorList = {"Red","Blue","Yellow"};
        MyDialogListColor(Frame owner) {
            super(owner);
            setLayout(new FlowLayout());
            list = new List();

            for (int i=0;i<colorList.length;i++) {
                list.add(colorList[i]);
            }
            add(list);
            Button b1 = new Button("OK");
            b1.addActionListener(this);
            add(b1);
            setTitle("MyDialog");
            setSize(80, 100);
        }
        public void actionPerformed(ActionEvent e) {
            if(list.getSelectedItem().equals("Blue")){
                clockFrame.setFontColor(Color.blue);
            }else if(list.getSelectedItem().equals("Red")){
                clockFrame.setFontColor(Color.red);
            }else if(list.getSelectedItem().equals("Yellow")) {
                clockFrame.setFontColor(Color.yellow);
            }
            hide();
        }
    }
    class MyDialogListFont extends Dialog implements ActionListener {
        List list;
        String[] fontList = {"MS Gothic",  // Microsoft ゴシックフォント
                "IPAGothic",      //IPA ゴシック
                "IPAMonaGothic"};
        MyDialogListFont(Frame owner) {
            super(owner);
            setLayout(new FlowLayout());
            list = new List();

            for (int i=0;i<fontList.length;i++) {
                list.add(fontList[i]);
            }
            add(list);
            Button b1 = new Button("OK");
            b1.addActionListener(this);
            add(b1);
            setTitle("MyDialogListFont");
            setSize(80, 100);
        }
        public void actionPerformed(ActionEvent e) {
            if(list.getSelectedItem().equals("MS Gothic")){
                clockFrame.setFontStr("MS Gothic");
            }else if(list.getSelectedItem().equals("IPAGothic")){
                clockFrame.setFontStr("IPAGothic");
            }else if(list.getSelectedItem().equals("IPAMonaGothic")) {
                clockFrame.setFontStr("IPAMonaGothic");
            }
            hide();
        }
    }
    class MyDialogFontSize extends Dialog implements ActionListener {
        TextField t1;
        MyDialogFontSize(Frame owner) {
            super(owner);
            setLayout(new FlowLayout());
            t1 = new TextField(String.valueOf(clockFrame.getFontSize()));
            add(t1);
            Button b1 = new Button("OK");
            b1.addActionListener(this);
            add(b1);
            setTitle("MyDialogListFont");
            setSize(80, 100);
        }
        public void actionPerformed(ActionEvent e) {
            clockFrame.setFontSize(Integer.parseInt(t1.getText()));
            hide();
        }
    }
    class MyDialogListBackColor extends Dialog implements ActionListener {
        List list;
        String[] colorList = {"Red","Blue","Yellow"};
        MyDialogListBackColor(Frame owner) {
            super(owner);
            setLayout(new FlowLayout());
            list = new List();

            for (int i=0;i<colorList.length;i++) {
                list.add(colorList[i]);
            }
            add(list);
            Button b1 = new Button("OK");
            b1.addActionListener(this);
            add(b1);
            setTitle("MyDialog");
            setSize(80, 100);
        }
        public void actionPerformed(ActionEvent e) {
            if(list.getSelectedItem().equals("Blue")){
                clockFrame.setBackColor(Color.blue);
            }else if(list.getSelectedItem().equals("Red")){
                clockFrame.setBackColor(Color.red);
            }else if(list.getSelectedItem().equals("Yellow")) {
                clockFrame.setBackColor(Color.yellow);
            }
            hide();
        }
    }
}
