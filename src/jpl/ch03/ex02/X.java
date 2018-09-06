package jpl.ch03.ex02;

public class X {
    protected static int xMask = 0x00ff;
    protected static int fullMask;
    {
        System.out.println("xMask\tyMask\tfullMask");
        System.out.printf("0x%04x 0x%04x 0x%04x\n",xMask,0x0000,fullMask);
    }

    public X(){
        fullMask = xMask;
        System.out.printf("0x%04x 0x%04x 0x%04x\n",xMask,0x0000,fullMask);
    }

    public int mask(int orig){
        return (orig & fullMask);
    }

}
