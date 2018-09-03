package jpl.ch01.ex08;

import jpl.ch01.text.Point;

/**
 * Pointクラスにメソッドを追加して，
 * 引数として渡されたオブジェクトの座標を自分の座標に設定するメソッドを定期しなさい
 */

public class L1_8 {
    public static void main(String[] args){
        Point p = new Point();
        p.setPoint(12,32);
        System.out.println( "x : " + p.x + "\ty : "+ p.y );
    }
}
