package reversi.input;

/**
 * フレーム内でgetpointをimplementする
 * 配列で渡さなくもROBを渡してよい
 * Masuがクリックされた時の処理をインターフェースを使ってframeで実装する
 */

import reversi.system.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiInputHundler implements PieceInputHandler,Runnable,ClickLintener{
    Thread th;
    Flow flow;
    Point activePoint;
    ReversiFrame reversiFrame;
    private boolean isClick;
    private int width;
    private int height;

    public GuiInputHundler(int width,int height,ReversiFrame reversiFrame) {
        this.width = width;
        this.height = height;
        isClick = false;
        this.reversiFrame = reversiFrame;
        th = new Thread(this);

    }
    public GuiInputHundler(){

    }

    public void start(){
        th.start();
    }

    @Override
    public Point getPoint(ReadOnlyBoard board, Piece piece) {
        Piece[][] tmpPiece = new Piece[board.getHeight()][board.getWidth()];
        while (!isClick){

        }
        System.out.println(activePoint.getX());

        return activePoint;
    }

    @Override
    public void MasuClick(Point point) {
        isClick = true;
        activePoint = point;

    }

    @Override
    public void onFinish(ReadOnlyBoard board) {

    }

    public void show(){

    }

    @Override
    public void run() {
        flow = new Flow(width,height,new GuiInputHundler());
        flow.start();

    }
}
