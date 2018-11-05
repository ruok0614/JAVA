package reversi.input;

import reversi.system.Board;
import reversi.system.Piece;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

public class ReversiFrame extends Frame {
    private Piece[][] board;
    private boolean isClick;

    public ReversiFrame(int width,int height){
        init();
        isClick = false;
        board = new Piece[height][width];
        addWindowListener(new Ada());

    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void init(){
        setTitle("リバーシ");
        setSize(800,800);
        setBackground(Color.BLACK);
        GridLayout gridLayout = new GridLayout(8,8);
        Masu[][] masu = new Masu[8][8];
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        setLayout(gridLayout);
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                //masu[y][x] = new Masu(x,y);
                add(masu[y][x]);
            }
        }


    }
    public Piece boardColor(int x,int y,Board board){
        return board.getPiece(x,y);
    }


}

