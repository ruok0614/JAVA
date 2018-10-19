package reversi.system;

public class Point {
    //ここは不変にする
    private final int x;
    private final int y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




}
