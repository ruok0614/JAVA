package jpl.ch09.ex02;

public class Ex02 {
    public static int bitCount(int i) {
        int count = 0;
        while (i != 0) {
            if ((i & 0x01) == 1) {
                count++;
                System.out.print(1);
            }else {
                System.out.print(0);
            }
            i = i >>> 1;
        }
        System.out.println();
        return count;
    }

    public static void main(String args[]) {
        int i=31;
        int count;
        count = bitCount(i);
        System.out.println(count);
    }
}

