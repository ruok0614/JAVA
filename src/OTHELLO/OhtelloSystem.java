package OTHELLO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class OhtelloSystem {
    public static final OthelloPiece.OthelloStatus BLACK = OthelloPiece.OthelloStatus.black;
    public static final OthelloPiece.OthelloStatus WHITE = OthelloPiece.OthelloStatus.white;

    public static void start(){
        OhtelloBoard ohtelloBoard = new OhtelloBoard();
        int c = 1;
        ohtelloBoard.show(BLACK);
        Scanner sc = new Scanner(System.in);
        start:while (true) {
            while (true) {
                int y = sc.nextInt();
                int x = sc.nextInt();
                if (c % 2 == 1) {
                    if (ohtelloBoard.setPiece(y, x, BLACK)) {
                        ohtelloBoard.show(WHITE);
                        if(ohtelloBoard.whiteNum + ohtelloBoard.blackNum == 64){
                            System.out.printf("黒:%d 白:%d \n",ohtelloBoard.blackNum,ohtelloBoard.whiteNum);
                            if(ohtelloBoard.blackNum > ohtelloBoard.whiteNum){
                                System.out.println("黒の勝ち");
                            }else if(ohtelloBoard.blackNum < ohtelloBoard.whiteNum){
                                System.out.println("白の勝ち");
                            }else{
                                System.out.println("引き分け");
                            }
                            break start;
                        }
                        break;
                    }
                } else {
                    if (ohtelloBoard.setPiece(y, x, WHITE)) {
                        ohtelloBoard.show(BLACK);
                        if(ohtelloBoard.whiteNum + ohtelloBoard.blackNum == 64){
                            System.out.printf("黒:%d 白:%d \n",ohtelloBoard.blackNum,ohtelloBoard.whiteNum);
                            if(ohtelloBoard.blackNum > ohtelloBoard.whiteNum){
                                System.out.println("黒の勝ち");
                            }else if(ohtelloBoard.blackNum < ohtelloBoard.whiteNum){
                                System.out.println("白の勝ち");
                            }else{
                                System.out.println("引き分け");
                            }
                            break start;
                        }
                        break;
                    }
                }
            }
            c++;
        }
    }
    public static void randomGame(){
        class OhtelloBoard2 extends OhtelloBoard{
            class Plot{
                int x;
                int y;
                Plot(int y,int x){
                    this.x = x;
                    this.y = y;
                }
            }
            List<Plot> canPut = new ArrayList<>();

            @Override
            void show(OthelloPiece.OthelloStatus status){
                blackNum = 0;
                whiteNum = 0;
                canPut.clear();
                System.out.print("  ０１２３４５６７８\n");
                for(int i = 0; i<MAX_ROW; i ++){
                    System.out.print(i + " ");
                    for(int j = 0; j<MAX_COLUM; j++){
                        if(OthelloBoard[i][j] == null ) {
                            if (checkSet(i, j, status) == true) {
                                canPut.add(new Plot(i,j));
                                System.out.print("＋");
                            } else if (checkSet(i, j, status) == false) {
                                System.out.print("ー");
                            }
                        }else if(OthelloBoard[i][j].getStatus() == BLACK){
                            System.out.print("●");
                            blackNum++;
                        }else if(OthelloBoard[i][j].getStatus() == WHITE) {
                            System.out.print("〇");
                            whiteNum++;
                        }
                    }
                    System.out.print("\n");
                }

            }
        }
        OhtelloBoard2 ohtelloBoard = new OhtelloBoard2();
        int c = 1;
        ohtelloBoard.show(BLACK);
        start:while (true) {
            while (true) {
                if (c % 2 == 1) {
                    Collections.shuffle(ohtelloBoard.canPut);
                    if (ohtelloBoard.setPiece(ohtelloBoard.canPut.get(0).y, ohtelloBoard.canPut.get(0).x, BLACK)) {
                        ohtelloBoard.show(WHITE);
                        if(ohtelloBoard.canPut.size() == 0){
                            ohtelloBoard.show(BLACK);
                            c++;
                        }
                        break;
                    }
                } else {
                    Collections.shuffle(ohtelloBoard.canPut);
                    if (ohtelloBoard.setPiece(ohtelloBoard.canPut.get(0).y, ohtelloBoard.canPut.get(0).x, WHITE))
                        ohtelloBoard.show(BLACK);
                        if(ohtelloBoard.canPut.size() == 0){
                            ohtelloBoard.show(WHITE);
                            c++;
                        }
                        break;
                    }
                }
            if(ohtelloBoard.whiteNum + ohtelloBoard.blackNum == 64){
                System.out.printf("黒:%d 白:%d \n",ohtelloBoard.blackNum,ohtelloBoard.whiteNum);
                if(ohtelloBoard.blackNum > ohtelloBoard.whiteNum){
                    System.out.println("黒の勝ち");
                }else if(ohtelloBoard.blackNum < ohtelloBoard.whiteNum){
                    System.out.println("白の勝ち");
                }else{
                    System.out.println("引き分け");
                }
                break start;
            }
            c++;
        }

    }

    public static void main(String args[]){
        start();
        //randomGame();
    }
}
