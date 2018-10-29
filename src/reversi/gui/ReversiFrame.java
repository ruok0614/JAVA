package reversi.gui;

import reversi.system.*;
import reversi.system.Point;

import java.awt.*;

public class ReversiFrame extends java.awt.Frame implements MasuClick, PieceInputHandler,Runnable {

    private int boardWidth;
    private int boardHeight;
    private Thread th;
    private Flow flow;
    private boolean isClick;
    private Point activePoint;
    private Masu[][] masu;

    public ReversiFrame(int boardWidth,int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        th = new Thread(this);
        init();
        start();
        isClick = false;
    }

    public void init(){
        setTitle("リバーシ");
        setSize(800,800);
        setBackground(Color.BLACK);
        addWindowListener(new CloseWindow());
        // ボードマス分マスクラスを生成
        GridLayout gridLayout = new GridLayout(boardWidth,boardHeight);
        masu = new Masu[boardHeight][boardWidth];
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        setLayout(gridLayout);
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < boardWidth; x++) {
                masu[y][x] = new Masu(x,y,this);
                add(masu[y][x]);
            }
        }
        // ボードの初期化
        masu[boardHeight/2-1][boardWidth/2-1].setPiece(Piece.WHITE);
        masu[boardHeight/2][boardWidth/2].setPiece(Piece.WHITE);
        masu[boardHeight/2-1][boardWidth/2].setPiece(Piece.BLACK);
        masu[boardHeight/2][boardWidth/2-1].setPiece(Piece.BLACK);

        repaint();

    }

    public void setClick(boolean click) {
        isClick = click;
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }


    @Override
    public Point getPoint(ReadOnlyBoard board, Piece piece){
        for(int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                masu[y][x].setPiece(board.getPiece(x,y));
            }
        }
        repaint();

        while (!isClick){

        }

        masu[activePoint.getY()][activePoint.getX()].setPiece(piece);
        repaint();
        setClick(false);
        return activePoint;
    }

    @Override
    public void onFinish(ReadOnlyBoard board) {

    }

    public void start(){
        System.out.println("start");
        th.start();
    }

    @Override
    public void run() {
        flow = new Flow(boardWidth,boardHeight,this);
        flow.start();

    }

    @Override
    public void masuClick(Point point) {
        setClick(true);
        activePoint = point;
        System.out.println(point.getX() + point.getY());
    }
}
