package reversi.input;

import reversi.system.Board;
import reversi.system.Piece;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

public class ReversiFrame extends Frame {

    public ReversiFrame(){
        init();

    }

    public void init(){
        setTitle("リバーシ");
        setSize(800,800);
        setBackground(Color.BLACK);
        GridLayout masu = new GridLayout(8,8);
        masu.setHgap(1);
        masu.setVgap(1);
        setLayout(masu);
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                add(new Masu(x,y));
            }
        }
    }
    public Piece boardColor(int x,int y,Board board){
        return board.getPiece(x,y);
    }


}

