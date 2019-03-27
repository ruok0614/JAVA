package InterPrinter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewとModelの中継役
 */
public class Controller {
    private List<IObserver> observer;
    private FieldHolder fieldHolder;

    Controller(){
        observer = new ArrayList<IObserver>();
    }
    /**
     * コンストラクタ取得ボタンを押したときの動作
     * @param inputText
     */
    public void pushedclassNameGetButton(String inputText){
        Member[] mem = ClassInfoSearcher.serchConstructor(inputText);
        FieldHolder fieldHolder = new FieldHolder();
//        fieldHolder.setConstructorList(mem);
        observer.get(0).showConstructor(mem);
    }

    public void selectedValue(String value) {
        Object a = fieldHolder.setSelectField(value);
    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(IObserver observer){
        this.observer.add(observer);
    }





}
