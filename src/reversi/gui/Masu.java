package reversi.gui;

import reversi.system.Piece;
import reversi.system.Point;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Masu extends Canvas  implements MouseListener {
    /** 円の半径 */
    private int radius = 80;
    MasuClick masuClick;
    private Point point;
    private Piece piece;
    private boolean canset;

    Masu(int x, int y, MasuClick masuClick){
        point = new Point(x, y);
        this.piece = Piece.NONE;
        setBackground(Color.GREEN);
        addMouseListener(this);
        this.masuClick = masuClick;
        canset = false;
    }

    public void setCanset(boolean canset) {
        this.canset = canset;
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
                setBackground(Color.GREEN);
                g.setColor( Color.WHITE);
                g.fillOval( w/2-radius/2, h/2-radius/2, radius, radius);
                break;
            case BLACK:
                setBackground(Color.GREEN);
                g.setColor( Color.BLACK);
                g.fillOval( w/2-radius/2, h/2-radius/2, radius, radius);
                break;
            case NONE:
                if(canset){
                    setBackground(Color.LIGHT_GRAY);
                }else {
                    setBackground(Color.GREEN);
                }
                g.drawRect(0, 0, w, h);
                break;
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Click  X=" + point.getX() + "  Y=" + point.getY());
        masuClick.masuClick(point);
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}
