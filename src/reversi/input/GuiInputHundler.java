package reversi.input;

import reversi.system.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiInputHundler implements PieceInputHandler,Runnable {
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

        while (true){
            if (Masu.getClick()){

                break;
            }
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
