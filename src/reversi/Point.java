package reversi;

public class Point {
    //ここは不変にする
    private final int x;
    private final int y;

    Point(int x,int y){
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
