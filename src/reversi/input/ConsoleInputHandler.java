package reversi.input;

import reversi.system.*;

import java.util.Scanner;

public class ConsoleInputHandler implements PieceInputHandler {
    /**
     * 標準入力から座標(x,y)を入力します
     * @param board ゲームで使用する盤
     * @param piece 自分の駒の色
     * @return Point型で座標(x,y)を返します
     */
    @Override
    public Point getPoint(ReadOnlyBoard board, Piece piece) {
        Scanner sc = new Scanner(System.in);
        System.out.print("  ");
        for(int i = 0; i<board.getWidth(); i++){
            System.out.printf("%2d",i);
        }
        System.out.println();
        for(int y = 0; y<board.getHeight(); y ++){
            System.out.printf("%2d",y);
            for(int x = 0; x<board.getWidth(); x++){
                if(board.getPiece(x,y) == Piece.NONE ) {
                    if (board.check(x, y, piece)) {
                        System.out.print("＋");
                    } else if (!board.check(x, y, piece)) {
                        System.out.print("‐");
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
    public void onFinish(ReadOnlyBoard board) {
        if(board.getCountOf(Piece.WHITE) < board.getCountOf(Piece.BLACK)){
            System.out.println("黒の勝ち");
        }else if(board.getCountOf(Piece.WHITE) > board.getCountOf(Piece.BLACK)){
            System.out.println("白の勝ち");
        }else{
            System.out.println("引き分け");
        }

    }
}
