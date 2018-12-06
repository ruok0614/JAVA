package jpl.ch14.ex01;

public class MainNameShow {
    public static void main(String args[]){
        String mainName;
        mainName = Thread.currentThread().getName();
        System.out.println(mainName);
    }
}
