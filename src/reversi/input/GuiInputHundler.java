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
    Point point;
    ReversiFrame reversiFrame;
    private int width;
    private int height;

    public GuiInputHundler(int width,int height,ReversiFrame reversiFrame) {
        this.width = width;
        this.height = height;

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
        while (Masu.getClick()){
            for(int y = 0; y < board.getHeight();y++){
                for(int x = 0; x < board.getWidth();x++){
                    tmpPiece[y][x] = board.getPiece(x,y);
                }
            }
            reversiFrame.setBoard(tmpPiece);

            break;
        }
        return Masu.getActivePoint();
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
