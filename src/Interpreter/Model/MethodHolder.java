package Interpreter.model;

import Interpreter.model.common.Result;

import java.lang.reflect.InvocationTargetException;
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
        try {
            methodlist = Arrays.asList(obj.getClass().getMethods());
        }catch (Exception e){
            observers.get(0).showMethodList(null);
            return;
        }
        observers.get(0).showMethodList(methodlist);

    }
    public Result invoke(int index, Object[] args, Object src) throws  IllegalAccessException {
        Method method = methodlist.get(index);
        Object returnValue;
        try {
                returnValue = method.invoke(src, args);

            return Result.createSuccess(returnValue);
        }
        catch (InvocationTargetException e) {
            String errorStr = e.getTargetException().toString();
            return Result.createFailure(errorStr);
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
