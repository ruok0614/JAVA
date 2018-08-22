package jpl.L1;

/**
 * 練習問題1.15
 * 　addとremoveメゾッドを宣言したインターフェイスを，lookupを拡張して定義しなさい．
 * その拡張したインターフェイスを新たなクラスに実装しなさい．
 */

public class L1_15tes {
    public static void main(String[] args){
        L1_15 table = new L1_15();
        String[] names = {"test", "abc"};

        /*二つの文字列を追加*/
        for(int i = 0; i < names.length;i++) {
            Object value = table.add(names[i]);
            if(value != null)
                System.out.println(i + ": " + value);
        }
        /*二つの文字列を追加されているか確認*/
        for(int i = 0; i < names.length;i++) {
            Object value = table.find(names[i]);
            if(value != null)
                System.out.println(i + ": " + value);
        }
        /*二つの文字列を削除*/
        for(int i = 0; i < names.length;i++) {
            Object value = table.remove(names[i]);
            if(value != null)
                System.out.println(value);
        }
        /*二つの文字列が削除されているか確認*/
        for(int i = 0; i < names.length;i++) {
            Object value = table.find(names[i]);
            if(value != null)
                System.out.println(i + ": " + value);
        }
    }
}
