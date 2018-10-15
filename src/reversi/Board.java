package reversi;

/**
 * 関数は主語がオブジェクトで関数が述語
 * ゲッターセッターはセットで置く
 * 全部の関数にドキュメントコメントを書く
 * 最初はインプットはんどらーのコンソール版を作る
 */
public class Board {

    private final Piece[][] board ;

    private final int width;
    private final int height;


    public Board(int width,int height){
        this.width = width;

        this.height = height;
        board = new Piece[height][width];
        init();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        getPiece(0,0);
        return height;
    }

    /**
     * 座標(x,y)の駒を取得します
     * @param x 取得する駒のx座標
     * @param y 取得する駒のy座標
     * @return 座標(x,y)の駒
     * @exception IndexOutOfBoundsException xまたはyが盤の範囲以外のとき
     */
    public Piece getPiece(int x,int y){

    }


    public boolean setPiece(int x,int y,Piece piece){
        // 反転の処理もここ：セットししたのに反転しないことはないため

    }

    public boolean check(int x,int y,Piece piece){

    }


    public int getCountOf(Piece piece){
        // 入れた色を数える
    }



    /**
     *
     */

    private void init(){
    // ボード初期化：今回は白黒2つ配置でよい

    }

    //
    private boolean trySetPiece(int x,int y,Piece piece,boolean acutuallySet){
        if(acutuallySet){
            //差分がある処理を書く
        }

    }






}
