package jpl.ch07.ex02;

public class ConvertType {
    public static void main(String[] args) {
        int tmpi=0;
        long tmpl=0;
        byte maxByte = 127;
        byte miniByte = -128;
        short maxShort = 32767;
        short miniShort = -32768;
        int maxInt = 2147483647;
        int miniInt = -2147483648;
        long maxLong = 9223372036854775807L;
        long miniLong = -9223372036854775808L;
        float maxFloat = 3.4028235E38F;
        float miniFloat = 1.4E-45F;
        double maxDouble = 1.7976931348623157E308D;
        double miniDouble = 4.9E-324D;

        System.out.println(maxByte);
        System.out.println(miniByte);
        System.out.println(maxShort);
        System.out.println(miniShort);
        System.out.println(maxInt);
        System.out.println(miniInt);
        System.out.println(maxLong);
        System.out.println(miniLong);
        System.out.println(maxFloat);
        System.out.println(miniFloat);
        System.out.println(maxDouble);
        System.out.println(miniDouble);
        System.out.println(tmpi=(int)miniByte);
        System.out.println(tmpi=(int)miniShort);
        System.out.println(tmpi=(int)miniLong);
        System.out.println(tmpi=(int)miniFloat);
        System.out.println(tmpl=(int)miniDouble);
        System.out.println(tmpl=miniByte);
        System.out.println(tmpl=miniShort);
        System.out.println(tmpl=miniLong);
        System.out.println(tmpl=(long)miniFloat);
        System.out.println(tmpl=(long)miniDouble);
    }

}
