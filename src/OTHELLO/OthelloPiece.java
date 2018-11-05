package OTHELLO;


public class OthelloPiece {

    enum OthelloStatus{
        black,
        white;
    }
    private int x;
    private int y;

    private OthelloStatus status;

    public OthelloStatus getStatus(){
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    OthelloPiece(int y,int x,OthelloStatus status){
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public void reverse(){
        if(status == OthelloStatus.black){
            status = OthelloStatus.white;
        }else if(status == OthelloStatus.white){
            status = OthelloStatus.black;
        }
    }

    public boolean equals(OthelloPiece piece){
        if(status == OthelloStatus.black){
            if(piece.status == OthelloStatus.black){
                return true;
            }else{
                return false;
            }
        }else if(status == OthelloStatus.white){
            if(piece.status == OthelloStatus.white){
                return true;
            }else{
                return false;
            }
        }
        return false;//とりあえずfalse
    }


}
