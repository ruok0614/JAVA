package InterPrinter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class ConstructorHolder {
    Member[] constructorlist;
    private List<IConstructorObserver> observers;

    ConstructorHolder( IConstructorObserver observer){
        observers = new ArrayList<IConstructorObserver>();
        addObserver(observer);
    }
    public void serchConstructor(String inputText){
        Class<?> clazz = null;
        try{
            clazz = Class.forName(inputText);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        constructorlist = clazz.getConstructors();
        pushedclassNameGetButton();
    }
    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(IConstructorObserver observer){
        this.observers.add(observer);
    }

    /**
     * コンストラクタ取得ボタンを押したときの動作
     */
    public void pushedclassNameGetButton(){
        observers.get(0).showConstructor(constructorlist);
    }

}
