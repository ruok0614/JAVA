package Interpreter.Model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodHolder {
    Method[] methodlist;
    private List<MethodHolderObserver> observers;
    public MethodHolder(){
        observers = new ArrayList<MethodHolderObserver>();
    }
    public void addMethodList(Object obj){
        methodlist = obj.getClass().getMethods();
        observers.get(0).showMethodList(methodlist);

        for (Method m:methodlist) {
            System.out.println(m);
        }

    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(MethodHolderObserver observer){
        this.observers.add(observer);
    }

}
