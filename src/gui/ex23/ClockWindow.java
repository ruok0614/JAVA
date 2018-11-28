package gui.ex23;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


/**
 * 課題 2-3 課題 2-２のデジタル時計を、次のように修正してください。
 * ✓ JFrame ではなく JWindow クラスを使用して、フレーム枠のないデジタル時計にする。
 * ✓ 課題 2-２のダイアログで指定できた属性は、マウスの右クリックでポップアップメニューを表示して、カスケード形式で選択出来るようにする（ダイアログは開かない）。
 * ✓ 時計内をマウスの左ボタンでクリックしたまま、デスクトップ上でウィンドウを移動させること ができるようにする。
 */
public class ClockWindow extends JWindow {

    private Container  content;
    private ClockPaint clockPaint;
    private PopUpMenue popUpMenue;
    private MouseEvent start; //WindowDrugMoveクラス内で使うがここに設置しないと動かない
    Map colorMap;
    String activeFontColor;
    InputHundler inputHundler;
    {
        colorMap = new HashMap<String, java.awt.Color>();
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("White", Color.WHITE);
        colorMap.put("Black",Color.BLACK);
        colorMap.put("Red",Color.RED );

    }

    public ClockWindow(JFrame owner){
        super(owner);
        setSize(300, 120);
        //位置を適切にしてくれるやつ
        setLocationByPlatform(true);
        setAlwaysOnTop(true); //常に前に表示


        addMouseListener( new MouseAction()); //マウスを最初に掴んだ時
        addMouseMotionListener( new MouseAction()); //マウスをドラッグした時
        popUpMenue = new PopUpMenue(){
                public void actionPerformed(ActionEvent e){
                    System.out.println(e.paramString());
                    activeFontColor = e.getActionCommand();
                }
        };

        clockPaint = new ClockPaint(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Font font = new Font("ＭＳ Ｐゴシック",Font.PLAIN,50);
                g.setColor((Color)colorMap.get(activeFontColor));
                setFont(font);
                g.drawString(String.format("%02d:%02d:%02d",clock.getH(),clock.getM(),clock.getS()),50,80);
            }
        };
        add(clockPaint);
    }


    class MouseAction extends MouseAdapter{
        private Point loc;
        public void mouseDragged(MouseEvent e){
            Window window = ClockWindow.this;
            loc = window.getLocation(loc);
            int x = loc.x - start.getX() + e.getX();
            int y = loc.y - start.getY() + e.getY();
            window.setLocation(x, y);
        }
        public void mousePressed(MouseEvent me) { start = me; } //最初に掴んだポイントを記憶
        public void mouseReleased(MouseEvent e){
            int btn = e.getButton();
            if(btn == MouseEvent.BUTTON3){
                popUpMenue.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

}
