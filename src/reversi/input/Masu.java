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


    Masu(int x, int y,ClickLintener clickLintener){
        point = new Point(x, y);
        this.piece = Piece.NONE;
        setBackground(Color.GREEN);
        addMouseListener(this);
        this.clickLintener = clickLintener;

    }

    public void setRadius( int r){
            this.radius = r;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void paint(Graphics g){
        int w = this.getWidth();
        int h = this.getHeight();
        switch (piece){
            case WHITE:
                g.setColor( Color.WHITE);
                g.fillOval( w/2-radius/2, h/2-radius/2, radius, radius);
                break;
            case BLACK:
                g.setColor( Color.BLACK);
                g.fillOval( w/2-radius/2, h/2-radius/2, radius, radius);
                break;
            case NONE:
                break;
        }

   }
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Click  X=" + point.getX() + "  Y=" + point.getY());
        clickLintener.MasuClick(point);
    }

    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}
