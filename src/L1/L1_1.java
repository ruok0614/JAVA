package L1;

/*
練習問題1.1
 　Hello World を入力，コンパイル，そして実行してください
 */
public class  L1_1{
    public static void main(String[] args){
        String message = args[0];
        System.out.println(makeMessage(message));
    }

    static String makeMessage(String message){
        return message;
    }
}
