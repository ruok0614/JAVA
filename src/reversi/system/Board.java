package reversi.system;

/**
 * 関数は主語がオブジェクトで関数が述語
 * ゲッターセッターはセットで置く
 * 全部の関数にドキュメントコメントを書く
 * 最初はインプットはんどらーのコンソール版を作る
 */
public class Board implements ReadOnlyBoard{
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        getPiece(0,0);
        return height;
    }

    public Piece getPiece(int x,int y){
        return board[y][x];
    }


    public boolean check(int x,int y,Piece piece){
        return trySetPiece(x,y,piece,false);
    }

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
     * ボードの座標(x,y)に駒をセットします．
     * その時，裏返せる駒があれば裏返します．
     * @param x　セットする駒のx座標
     * @param y　セットする駒のy座標
     * @param piece セットする駒の色
     * @return 駒がセットできたかの真偽
     */
    //package
    boolean setPiece(int x,int y,Piece piece){
        // 反転の処理もここ：セットししたのに反転しないことはないため
        return trySetPiece(x,y,piece,true);
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

        board[getHeight()/2-1][getWidth()/2-1] = Piece.WHITE;
        board[getHeight()/2][getWidth()/2] = Piece.WHITE;
        board[getHeight()/2-1][getWidth()/2] = Piece.BLACK;
        board[getHeight()/2][getWidth()/2-1] = Piece.BLACK;

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
        if(getPiece(x,y) != Piece.NONE){
            return false;
        }

        for(int i = 0; i < DIRECTION_NUM; i++){
            int varY = y; //stepY
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
                if(piece == getPiece(varX,varY) && acutuallySet){
                    varY -= allDirection[i][Y];
                    varX -= allDirection[i][X];
                    do {
                        board[varY][varX] = piece == Piece.BLACK?Piece.BLACK:Piece.WHITE;
                        varY -= allDirection[i][Y];
                        varX -= allDirection[i][X];
                    }while(varX != x && varY != y);
                    flag = true;
                    break;
                }else if(piece == getPiece(varX,varY) && !acutuallySet){
                    return true;
                }
            }
        }
        if(flag && acutuallySet){
            board[y][x] = piece == Piece.BLACK?Piece.BLACK:Piece.WHITE;
        }
        return flag;
    }






}
