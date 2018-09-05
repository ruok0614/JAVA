package jpl.ch03.ex02;

public class Y extends X{
    protected static int yMask = 0xff00;

    public Y(){
        super();
        System.out.printf("0x%04x 0x%04x 0x%04x\n",xMask,yMask,fullMask);
        fullMask |= yMask;
        }

    public static void main(String args[]){
        Y y = new Y();
        System.out.printf("0x%04x 0x%04x 0x%04x\n",xMask,yMask,fullMask);
    }

}
