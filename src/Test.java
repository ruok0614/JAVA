import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JDialog{
    protected Container pane;
    protected JPanel jpane;
    private MouseEvent start; //WindowDrugMoveクラス内で使うがここに設置しないと動かない

    Test(){
        super(new JFrame());
        setSize(300, 80);
        pane = getContentPane();

        jpane = new JPanel();
        jpane.setBackground(Color.green);
        pane.add(jpane);
        jpane.addMouseListener( new WindowDrugMove() ); //マウスを最初に掴んだ時
        jpane.addMouseMotionListener( new WindowDrugMove() ); //マウスをドラッグした時
        }



    class WindowDrugMove implements MouseMotionListener, MouseListener{
        private Point loc;
        public void mouseMoved(MouseEvent me){}
        public void mouseDragged(MouseEvent me){
            Window window = Test.this;
            loc = window.getLocation(loc);
            int x = loc.x - start.getX() + me.getX();
            int y = loc.y - start.getY() + me.getY();
            window.setLocation(x, y);
            }

        public void mouseClicked(MouseEvent me){}
        public void mouseEntered(MouseEvent me){}
        public void mouseExited(MouseEvent me){}
        public void mousePressed(MouseEvent me) { start=me; } //最初に掴んだポイントを記憶
        public void mouseReleased(MouseEvent me){}
        }

    public static void main(String[] args){
        Test d = new Test();
        d.setUndecorated(true); //タイトルバーなしウィンドウ
        d.setAlwaysOnTop(true); //常に前に表示
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        }
}