package jpl.ch09.ex01;

public class Ex01 {
    public static void main(String args[]){
        Float pi;
        Float ni;
        pi = Float.POSITIVE_INFINITY;
        ni = Float.NEGATIVE_INFINITY;

        System.out.println(pi + pi);
        System.out.println(ni + ni);
        System.out.println(pi + ni);
        System.out.println(pi - pi);
        System.out.println(ni - ni);
        System.out.println(pi - ni);
        System.out.println(ni - pi);
        System.out.println(pi * pi);
        System.out.println(ni * ni);
        System.out.println(pi * ni);
        System.out.println(pi / pi);
        System.out.println(ni / ni);
        System.out.println(pi / ni);
        System.out.println(ni / pi);
        System.out.println(pi % pi);
        System.out.println(ni % ni);
        System.out.println(pi % ni);
        System.out.println(ni % pi);

    }
}
