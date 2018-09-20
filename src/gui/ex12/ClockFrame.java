package gui.ex12;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;


public class ClockFrame extends Frame implements ActionListener{

    Clock clock;
    Thread th;
    Image buffer;
    Graphics bufferg;
    ClockMenu clockMenu;
    java.util.List<java.awt.Color> colorList = new java.util.ArrayList<java.awt.Color>();

    private Color fontColor;
    private Color backColor;
    private String fontStr;
    private int fontSize;


    ClockFrame(){
        clock = new Clock();
        setTitle("Clock");
        setSize(300, 160);

        colorList.add(Color.black);
        colorList.add(Color.blue);
        colorList.add(Color.white);
        colorList.add(Color.red);
        colorList.add(Color.yellow);


        setFontColor(Color.black);
        setFontSize(50);
        setBackColor(Color.white);
        setFontStr("ＭＳ Ｐゴシック");

        clockMenu = new ClockMenu(this);
        setMenuBar(clockMenu.menuBar);

        addWindowListener(new Ada());
        clock.th.start();

    }

    public void setFontColor(Color color){
        this.fontColor = color;
    }

    public void setBackColor(Color color){
        this.backColor = color;
    }

    public void setFontStr(String fontStr){
        this.fontStr = fontStr;
    }

    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
    }

    public int getFontSize(){
        return fontSize;
    }
    public void actionPerformed(ActionEvent e) {
        Collections.shuffle(colorList);
        setBackColor(colorList.get(0));
        setFontColor(colorList.get(1));
    }
    @Override
    public void paint(Graphics g)
    {   //バッファのグラフィックコンテキストを取得する
        //bufferg = buffer.getGraphics();
        //バッファを描画する

        Dimension d = getSize();
        buffer = createImage(d.width,d.height);
        bufferg = buffer.getGraphics();
        Graphics strg = buffer.getGraphics();

        bufferg.setColor(backColor);
        bufferg.fillRect(0,0,d.width,d.height);//内部を塗りつぶした四角形を生成

        Font font = new Font(fontStr,Font.PLAIN,fontSize);
        strg.setColor(fontColor);
        strg.setFont(font);
        strg.drawString(String.format("%02d:%02d:%02d",clock.h,clock.m,clock.s),50,100);
        g.drawImage(buffer,0,0,this);


    }



    @Override
    public void update(Graphics g){
        paint(g);
    }

    void clockRun(){
        boolean a = true;
        while (a==true){
            repaint();
            try{
                clock.th.sleep(100);  //スリープ１秒
            }catch(InterruptedException e){

            }
        }
    }

}
