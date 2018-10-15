package reversi;


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

    public void start(){
        Piece currentColor = Piece.BLACK;

        while(!isFinish()){
            if(!canSetAny(currentColor)){
                currentColor = currentColor == Piece.BLACK?Piece.WHITE:Piece.BLACK;
                continue;
            }
            boolean hasSet;
            do {
                Point point = inputHandler.getPoint(board);
                hasSet = board.setPiece(point.getX(), point.getY(), currentColor);
            }while (!hasSet);
            currentColor = currentColor == Piece.BLACK?Piece.WHITE:Piece.BLACK;
        }
        inputHandler.onFinish(board);
    }

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

    private boolean isFinish(){
        return !canSetAny(Piece.BLACK) && !canSetAny(Piece.WHITE);
    }


}
