package L1;

/**
 * 練習問題 1.10
 *  ImproveFibonacciアプリケーションを修正して，数列を配列に保存するようにしなさい．
 *  その際に，数列の値とその値が偶数かを示すブール値を保持するクラスを作成して，
 *  そのクラスのオブジェクトへの参照を配列として持つようにしなさい．
 */

public class L1_10 {
    static final int MAX_INDEX = 9;
    public static void main(String[] args){

        ImprovedFibonacciArray[] fibo = new ImprovedFibonacciArray[MAX_INDEX];
        for( int i = 0; i < MAX_INDEX; i++) {
            fibo[i] = new ImprovedFibonacciArray();
        }

        fibo[0].setNum(1);
        fibo[1].setNum(1);

        for (int i = 2; i < fibo.length; i++) {
            fibo[i].setNum(fibo[i-2].num + fibo[i-1].num);
        }
        for (int i = 0; i < fibo.length; i++){
            System.out.println(i + ":"  + fibo[i].num + " " + fibo[i].isEvenNumber);
        }
    }
}
