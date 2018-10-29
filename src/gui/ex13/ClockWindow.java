package gui.ex13;

import gui.ex12.ClockFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Thread.sleep;

public class ClockWindow extends Window implements MouseListener {

    private Thread th;
    private Clock clock;
    private Image buffer;
    private Graphics bufferg;
    private PopUpMenue popUpMenue;

    public ClockWindow(Frame owner) {
        super(owner);
        setSize(300,150);
        clock = new Clock();
        popUpMenue = new PopUpMenue();
        add(popUpMenue);
        addMouseListener(this);
        th = new Thread(clock);
        th.start();
    }

    public void paint(Graphics g){
        Dimension d = getSize();
        buffer = createImage(d.width,d.height);
        bufferg = buffer.getGraphics();
        Graphics strg = buffer.getGraphics();

        bufferg.setColor(Color.white);
        bufferg.fillRect(0,0,d.width,d.height);//内部を塗りつぶした四角形を生成

        Font font = new Font("ＭＳ Ｐゴシック",Font.PLAIN,50);
        strg.setColor(Color.BLACK);
        strg.setFont(font);
        strg.drawString(String.format("%02d:%02d:%02d",clock.getH(),clock.getM(),clock.getS()),50,100);
        g.drawImage(buffer,0,0,this);

    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    public void clockStart(){
        while (true){
            repaint();
            try{
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
        int btn = e.getButton();
        if(btn == MouseEvent.BUTTON3){
            popUpMenue.show(this,e.getX(),e.getY());
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
