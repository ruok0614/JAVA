package jpl.ch03.ex02;

public class Y extends X {
    protected int yMask = 0xff00;

    public Y(){
        fullMask |= yMask;
    }

    public static void main(String args[]){
        Y y = new Y();
        System.out.printf("0x%-4x 0x%-4x 0x%-4x",y.xMask,y.yMask,y.fullMask);
    }
}
