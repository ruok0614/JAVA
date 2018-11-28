package jpl.ch13.ex1;

public class CountCh {
    static public void countCh(String in,char find){
        int start = 0;
        int count = 0;
        while(in.indexOf(find,start) != -1){
            count ++;
            start = in.indexOf(find,start)+1;
        }

        System.out.println(find + "の出現回数：" + count);


    }
    static public void main(String[] args){
        CountCh.countCh("addaddada",'a');
    }
}
