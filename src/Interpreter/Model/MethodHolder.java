package Interpreter.Model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MethodHolder {
    List<Method> methodlist;

    public List<MethodHolderObserver> getObservers() {
        return observers;
    }

    private List<MethodHolderObserver> observers;
    public MethodHolder(){
        observers = new ArrayList<MethodHolderObserver>();
    }
    public void addMethodList(Object obj){
        methodlist = Arrays.asList(obj.getClass().getMethods());
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
