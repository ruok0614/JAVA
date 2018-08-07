package L1;

import java.util.ArrayList;

/**
 * 練習問題1.15
 * 　addとremoveメゾッドを宣言したインターフェイスを，lookupを拡張して定義しなさい．
 * その拡張したインターフェイスを新たなクラスに実装しなさい．
 */

public class L1_15 implements Operation {
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Object> values = new ArrayList<Object>();

    public Object find(String name) {
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).equals(name))
                return values.get(i);
        }
        return null; //見つからなかった
    }

    public Object add(String name) {
        names.add(name);
        values.add(values.size());
        return names.get(names.size()-1);
    }

    public Object remove(String name) {
        for(int i = 0; i < names.size(); i++){
            if(names.get(i).equals(name))
                names.remove(i);
                return true;
        }
        return null; //見つからなかった
    }


}

interface Lookup{
    /*nameと関連された値を返す
    * 値がなければnullを返す*/
    Object find(String name);
}

/*Lookupを継承してadd,removeを追加*/
interface Operation extends Lookup {
    /*nameを追加*/
    Object add(String name);
    /*nameを削除*/
    Object remove(String name);
}