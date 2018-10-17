package reversi;

import java.util.Scanner;

public class ReversiCocoleMain implements PieceInputHandler{
    /**
     * 標準入力から座標(x,y)を入力します
     * @param board ゲームで使用する盤
     * @param piece 自分の駒の色
     * @return Point型で座標(x,y)を返します
     */
    @Override
    public Point getPoint(Board board, Piece piece) {
        Scanner sc = new Scanner(System.in);
        System.out.print("  ０１２３４５６７\n");
        for(int y = 0; y<board.getHeight(); y ++){
            System.out.print(y + " ");
            for(int x = 0; x<board.getHeight(); x++){
                if(board.getPiece(x,y) == Piece.NONE ) {
                    if (board.check(x, y, piece)) {
                        System.out.print("＋");
                    } else if (!board.check(x, y, piece)) {
                        System.out.print("ー");
                    }
                }else if(board.getPiece(x,y) == Piece.BLACK){
                    System.out.print("●");
                }else if(board.getPiece(x,y) == Piece.WHITE) {
                    System.out.print("〇");
                }
            }
            System.out.print("\n");
        }
        System.out.print("y:");
        int y = sc.nextInt();
        System.out.print("x:");
        int x = sc.nextInt();
        Point point = new Point(x,y);
        return point;
    }

    /**
     * ゲーム終了時に勝者を表示します
     * @param board　ゲームで使用する盤d
     */
    @Override
    public void onFinish(Board board) {
        if(board.getCountOf(Piece.WHITE) < board.getCountOf(Piece.BLACK)){
            System.out.println("黒の勝ち");
        }else {
            System.out.println("白の勝ち");
        }

    }

    public static void main(String args[]){
        ReversiCocoleMain reversiCocoleMain = new ReversiCocoleMain();
        Flow flow = new Flow(8,8,reversiCocoleMain);
        flow.start();
    }
}
