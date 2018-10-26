package reversi.input;

import reversi.system.Piece;
import reversi.system.Point;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Masu extends Canvas implements MouseListener{
    /** 円の半径 */
    private int radius = 80;
    private Point point;
    private Piece piece;
    ClickLintener clickLintener;


    Masu(int x, int y){
        point = new Point(x, y);
        //this.piece = piece;
        setBackground(Color.GREEN);
        addMouseListener(this);
    }

    public void setRadius( int r){
            this.radius = r;
    }

    public Piece getPiece() {
        return piece;
    }


    public void paint(Graphics g){
        int w = this.getWidth();
        int h = this.getHeight();
        // キャンバスの中心に青い球を表示
        g.setColor( Color.BLACK);
//        if( getPiece() == Piece.BLACK){
//            g.setColor( Color.BLACK);
//        }else if(getPiece() == Piece.WHITE) {
//            g.setColor(Color.white);
//        }

        g.fillOval( w/2-radius/2, h/2-radius/2, radius, radius);
   }
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Click  X=" + point.getX() + "  Y=" + point.getY());
        clickLintener.MasuClick();
    }

    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}
