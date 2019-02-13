package jpl.ch13.ex2;

public class CountStr {
    static public void countStr(String in,String find){
        int start = 0;
        int count = 0;
        while(in.indexOf(find,start) != -1){
            count ++;
            start = in.indexOf(find,start)+1;
        }

        System.out.println(find + "の出現回数：" + count);


    }
    static public void main(String[] args){
        CountStr.countStr("aaddaddadaaddddadaad","aa");
    }
}
