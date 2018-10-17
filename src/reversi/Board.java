package reversi;

/**
 * 関数は主語がオブジェクトで関数が述語
 * ゲッターセッターはセットで置く
 * 全部の関数にドキュメントコメントを書く
 * 最初はインプットはんどらーのコンソール版を作る
 */
public class Board {
    public static final int DIRECTION_NUM = 8;
    public static final int X = 1;
    public static final int Y = 0;

    private final Piece[][] board;
    static final int[][] allDirection = {
            {0,-1},//左
            {-1,-1},//左上
            {-1,0},//上
            {-1,1},//右上
            {0,1},//右
            {1,1},//右下
            {1,0},//下
            {1,-1},//左下
    };

    private final int width;
    private final int height;


    public Board(int width,int height){
        this.width = width;
        this.height = height;
        board = new Piece[height][width];
        init();
    }

    /**
     * ボードの横マス長さを取得します
     * @return ボードの横マス長さ
     */
    public int getWidth() {
        return width;
    }

    /**
     * ボードの縦マス長さを取得します
     * @return ボードの縦マス長さ
     */
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
        return board[y][x];
    }

    /**
     * ボードの座標(x,y)に駒をセットします．
     * その時，裏返せる駒があれば裏返します．
     * @param x　セットする駒のx座標
     * @param y　セットする駒のy座標
     * @param piece セットする駒の色
     * @return 駒がセットできたかの真偽
     */
    public boolean setPiece(int x,int y,Piece piece){
        // 反転の処理もここ：セットししたのに反転しないことはないため
        boolean flag = false;
        for(int i = 0; i < DIRECTION_NUM; i++){
            int varY = y;
            int varX = x;
            varY += allDirection[i][Y];
            varX += allDirection[i][X];
            if(checkIndexoutOrNone(varX,varY)){
                continue;
            }

            while(piece != getPiece(varX,varY)){
                varY += allDirection[i][Y];
                varX += allDirection[i][X];
                if(checkIndexoutOrNone(varX,varY)){
                    break;
                }
                if(piece == getPiece(varX,varY)){
                    varY -= allDirection[i][Y];
                    varX -= allDirection[i][X];
                    do {
                        board[varY][varX] = piece == Piece.BLACK?Piece.BLACK:Piece.WHITE;
                        varY -= allDirection[i][Y];
                        varX -= allDirection[i][X];
                    }while(varX != x && varY != y);
                    flag = true;
                    break;
                }
            }
        }
        if(flag){
            board[y][x] = piece == Piece.BLACK?Piece.BLACK:Piece.WHITE;
        }
        return flag;
    }
    /**
     * ボードの座標(x,y)に駒に駒が置けるかチェックします．
     * @param x　チェックする駒のx座標
     * @param y　チェックする駒のy座標
     * @param piece チェックする駒の色
     * @return 駒が置けるかの真偽
     */
    public boolean check(int x,int y,Piece piece){
        for(int i = 0; i < DIRECTION_NUM; i++){
            int varY = y;
            int varX = x;
            varY += allDirection[i][Y];
            varX += allDirection[i][X];
            if(checkIndexoutOrNone(varX,varY)){
                continue;
            }
            while(piece != getPiece(varX,varY)){
                varY += allDirection[i][Y];
                varX += allDirection[i][X];
                if(checkIndexoutOrNone(varX,varY)){
                    break;
                }
                if(piece == getPiece(varX,varY)){
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * ボード座標(x,y)がボードの範囲外またはNONEでないか
     * @param x　チェックする駒のx座標
     * @param y　チェックする駒のy座標
     * @return 駒が置けるかの真偽
     */
    private boolean checkIndexoutOrNone(int x,int y){
        if( ( y < 0 || y  >= height ) || ( x < 0 || x >= width ) ){
            //System.out.println("ボード範囲外です");
            return true;
        }
        //nullでないか
        if (getPiece(x,y) == Piece.NONE ) {
            //System.out.println("nullです");
            return true;
        }
        return false;
    }

    /**
     * 指定した駒の色を数える
     * @param piece
     * @return
     */
    public int getCountOf(Piece piece){
        // 入れた色を数える
        int count = 0;
        for (int i = 0; i < height; i++){
            for (int j = 0;j < width; j++ ){
                if( board[i][j] == piece) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * ボードの初期化を行います
     *
     */
    private void init(){
    // ボード初期化：今回は白黒2つ配置でよい
        for (int i = 0; i < height; i++){
            for (int j = 0;j < width; j++ ){
                board[i][j] = Piece.NONE;
            }
        }

        board[3][3] = Piece.WHITE;
        board[4][4] = Piece.WHITE;
        board[3][4] = Piece.BLACK;
        board[4][3] = Piece.BLACK;

    }

    /**
     *
     * @param x
     * @param y
     * @param piece
     * @param acutuallySet tureならset
     * @return
     */
    private boolean trySetPiece(int x,int y,Piece piece,boolean acutuallySet){
        boolean flag = false;
        for(int i = 0; i < DIRECTION_NUM; i++){
            y += allDirection[i][Y];
            x += allDirection[i][X];
            if(checkIndexoutOrNone(x,y)){
                continue;
            }
            while(piece != getPiece(x,y)){
                y += allDirection[i][Y];
                x += allDirection[i][X];
                if(checkIndexoutOrNone(x,y)){
                    break;
                }
                if(piece == getPiece(x,y) && acutuallySet){
                    do {
                        y -= allDirection[i][Y];
                        x -= allDirection[i][X];
                        board[y][x] = piece == Piece.BLACK ? Piece.WHITE : Piece.BLACK;
                    } while (piece != getPiece(x, y));
                    flag = true;
                    break;
                }else if(!acutuallySet){
                    return true;
                }
            }
        }
        return flag;
    }






}
