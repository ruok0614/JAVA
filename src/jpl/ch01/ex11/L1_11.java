package jpl.ch01.ex11;

/**
 *練習問題 1.11
 *　StringsDemoアプリケーションを修正して，他の文字列を使用してみてください
 */
public class L1_11 {
    public static void main(String args[]){
        //StringBuilder:文字列操作が可能
        //StringBuffer:基本的にStringBuilderと同じだが，マルチスレッド
        StringBuilder myName = new StringBuilder("Petronius");
        String occupation = "Reorganization Specialist";

        myName.append(" Arbiter");
        myName.append(" ");
        myName.append("(" + occupation + ")");
        System.out.println("Name = " + myName);

    }
}
