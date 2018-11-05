package OTHELLO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OhtelloBoard {
    public static final int MAX_ROW = 8;
    public static final int MAX_COLUM = 8;
    public static final int DIRECTION_NUM = 8;
    public static final int X = 1;
    public static final int Y = 0;

    public static final OthelloPiece.OthelloStatus BLACK = OthelloPiece.OthelloStatus.black;
    public static final OthelloPiece.OthelloStatus WHITE = OthelloPiece.OthelloStatus.white;
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

    OthelloPiece[][] OthelloBoard;
    public int blackNum;
    public int whiteNum;

    OhtelloBoard(){
        boardInit();
    }

    void boardInit(){

        OthelloBoard = new OthelloPiece[MAX_ROW][MAX_COLUM];
        OthelloBoard[3][3] = new OthelloPiece(3,3,WHITE);
        OthelloBoard[4][4] = new OthelloPiece(4,4,WHITE);
        OthelloBoard[3][4] = new OthelloPiece(3,4,BLACK);
        OthelloBoard[4][3] = new OthelloPiece(4,3,BLACK);
    }

    boolean setPiece(int row, int column, OthelloPiece.OthelloStatus status){

        if(checkSet(row, column,status)){
            System.out.printf("set x:%d y:%d \n",row,column);
            OthelloBoard[row][column] = new OthelloPiece(row,column,status);
            reverse(OthelloBoard[row][column]);
            return true;

        }else{
            System.out.println("ここにはおけません");
            return false;
        }

    }
    public void reverse(OthelloPiece piece){

        for(int i = 0; i < DIRECTION_NUM; i++) {
            int y = piece.getY();
            int x = piece.getX();
            y += allDirection[i][Y];
            x += allDirection[i][X];
            if( ( y<0 || y>=MAX_ROW ) || ( x<0 || x>=MAX_COLUM ) ){
                //System.out.println("ボード範囲外です");
                continue;
            }
            //nullでないか
            if (OthelloBoard[y][x] == null ) {
                //System.out.println("nullです");
                continue;
            }
            // 自分とは違う色
            if (piece.equals(OthelloBoard[y][x]) == false) {
                // 先のマスをチェック
                List<OthelloPiece> tmpPieceList = new ArrayList<>();
                tmpPieceList.add(OthelloBoard[y][x]);
                while (true) {
                    y += allDirection[i][Y];
                    x += allDirection[i][X];
                    if( ( y<0 || y>=MAX_ROW ) || ( x<0 || x>=MAX_COLUM ) ){
                        //System.out.println("ボード範囲外です");
                        break;
                    }
                    // nullである
                    if (OthelloBoard[y][x] == null) {
                        break;
                    }// 自分と同じ色である
                    else if (piece.equals(OthelloBoard[y][x]) == true) {
                        for(OthelloPiece p : tmpPieceList){
                            System.out.printf("reverse x:%d y:%d \n",p.getX(),p.getY());
                            p.reverse();
                        }
                        break;
                    } //自分と違う色である
                    else if (piece.equals(OthelloBoard[y][x]) == false) {
                        tmpPieceList.add(OthelloBoard[y][x]);
                        continue;
                    }
                }
            }
        }

    }

    boolean checkSet(int row,int column,OthelloPiece.OthelloStatus status){
        OthelloPiece tmpPiece = new OthelloPiece(row,column,status);
        boolean res = false;
        //8方向を捜査
        for(int i = 0; i < DIRECTION_NUM; i++) {
            int y = row;
            int x = column;
            y += allDirection[i][Y];
            x += allDirection[i][X];
            if( ( y<0 || y>=MAX_ROW ) || ( x<0 || x>=MAX_COLUM ) ){
                //System.out.println("ボード範囲外です");
                continue;
            }
            //nullでないか
            if (OthelloBoard[y][x] == null ) {
                continue;
            }
            // 自分とは違う色
            if (tmpPiece.equals(OthelloBoard[y][x]) == false) {
                // 先のマスをチェック
                while (true) {
                    y += allDirection[i][Y];
                    x += allDirection[i][X];
                    if( ( y<0 || y>=MAX_ROW ) || ( x<0 || x>=MAX_COLUM ) ){
                        //System.out.println("ボード範囲外です");
                        break;
                    }
                    // nullである
                    if (OthelloBoard[y][x] == null) {
                        break;
                    }// 自分と同じ色である
                    else if (tmpPiece.equals(OthelloBoard[y][x]) == true) {
                        res = true;
                        break;
                    } //自分と違う色である
                    else if (tmpPiece.equals(OthelloBoard[y][x]) == false) {
                        continue;
                    }else {
                        break;
                    }
                }

            }
        }
        return res;
    }

    void show(OthelloPiece.OthelloStatus status){
        blackNum = 0;
        whiteNum = 0;
        System.out.print("  ０１２３４５６７\n");
        for(int i = 0; i<MAX_ROW; i ++){
            System.out.print(i + " ");
            for(int j = 0; j<MAX_COLUM; j++){
                if(OthelloBoard[i][j] == null ) {
                    if (checkSet(i, j, status) == true) {

                        System.out.print("＋");
                    } else if (checkSet(i, j, status) == false) {
                        System.out.print("ー");
                    }
                }else if(OthelloBoard[i][j].getStatus() == BLACK){
                    blackNum++;
                    System.out.print("●");
                }else if(OthelloBoard[i][j].getStatus() == WHITE) {
                    whiteNum++;
                    System.out.print("〇");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        OhtelloBoard a = new OhtelloBoard();
        a.boardInit();
        int c = 1;
        a.show(BLACK);
        while (true) {
            while (true) {
                int y = sc.nextInt();
                int x = sc.nextInt();
                if (c % 2 == 1) {
                    if (a.setPiece(y, x, BLACK)) {
                        a.show(WHITE);
                        break;
                    }
                } else {
                    if (a.setPiece(y, x, WHITE)) {
                        a.show(BLACK);
                        break;
                    }
                }
            }
            c++;
        }
    }
}
