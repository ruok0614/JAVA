package reversi.system;

public interface ReadOnlyBoard {
    /**
     * ボードの横マス長さを取得します
     * @return ボードの横マス長さ
     */
    int getWidth();

    /**
     * ボードの縦マス長さを取得します
     * @return ボードの縦マス長さ
     */
    int getHeight();

    /**
     * 座標(x,y)の駒を取得します
     * @param x 取得する駒のx座標
     * @param y 取得する駒のy座標
     * @return 座標(x,y)の駒
     * @exception IndexOutOfBoundsException xまたはyが盤の範囲以外のとき
     */
    Piece getPiece(int x,int y);


    /**
     * ボードの座標(x,y)に駒に駒が置けるかチェックします．
     * @param x　チェックする駒のx座標
     * @param y　チェックする駒のy座標
     * @param piece チェックする駒の色
     * @return 駒が置けるかの真偽
     */
    boolean check(int x,int y,Piece piece);

    /**
     * 指定した駒の色を数える
     * @param piece
     * @return
     */
    int getCountOf(Piece piece);
}
