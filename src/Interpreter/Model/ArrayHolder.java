package Interpreter.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayHolder {
    private List<Object> array;
    private List<ArrayHolderObserver> observers;

    public List<ArrayHolderObserver> getObservers() {
        return observers;
    }

    public ArrayHolder(){
        observers = new ArrayList<ArrayHolderObserver>();
    }

    /**
     *
     * @param obj
     */
    public void addArrayList(Object obj){
        Class<?> type =  obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        array = new ArrayList<>();
        for(int i = 0; i < length; i++){
            array.add(Array.get(obj, i));
        }
        observers.get(0).showArray(array);

    }

    public static <T> T[] newArray(Class<T> c,int len) {
        return (T[]) Array.newInstance(c, len);
    }

    public void addObserver(ArrayHolderObserver arrayHolderObserver){
        this.observers.add(arrayHolderObserver);
    }



}
