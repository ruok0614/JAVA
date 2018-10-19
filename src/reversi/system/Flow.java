package reversi.system;


/*
mainクラスは別に作る
 */
public class Flow {
    private Board board;
    private PieceInputHandler inputHandler;

    public Flow(int width, int height,PieceInputHandler inputHandler){
        board = new Board(width,height);
        this.inputHandler = inputHandler;
    }

    /**
     * ゲームをスタートします
     */
    public void start(){
        Piece currentColor = Piece.BLACK;

        while(!isFinish()){
            if(!canSetAny(currentColor)){
                currentColor = currentColor == Piece.BLACK?Piece.WHITE:Piece.BLACK;
                continue;
            }
            boolean hasSet;
            do {
                Point point = inputHandler.getPoint(board,currentColor);
                hasSet = board.setPiece(point.getX(), point.getY(), currentColor);
                System.out.println(hasSet);
            }while (!hasSet);
            currentColor = currentColor == Piece.BLACK?Piece.WHITE:Piece.BLACK;
        }
        inputHandler.onFinish(board);
    }

    /**
     * 自分の駒が置ける場所があるかチェックします
     * @param piece 自分の駒の色
     * @return 置ける場合trueを返します
     */
    private boolean canSetAny(Piece piece){
        for (int x = 0; x < board.getWidth(); x++){
            for (int y = 0; y < board.getHeight(); y++){
                if(board.check(x,y,piece)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ゲームが終了の判定をします
     * @return 終了の場合tureを返します
     */
    private boolean isFinish(){
        return !canSetAny(Piece.BLACK) && !canSetAny(Piece.WHITE);
    }


}
