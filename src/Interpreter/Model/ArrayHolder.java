package Interpreter.model;

import Interpreter.model.common.Result;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayHolder {
    private List<Object> array;
    private List<ArrayHolderObserver> observers;
    private String name;
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
    public void addArrayList(Object obj,String name){
        this.name = name;
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

    public Object searchArrayValue(int index, String name){
        if(!this.name.equals(name) || array.size() <= index){
            throw new IllegalArgumentException();
        }
        return array.get(index);
    }

    public Result setValue(int index,Object value){
        if(index > array.size()){
            return Result.createFailure(new ArrayIndexOutOfBoundsException());
        }
        if(value == null){
            return Result.createFailure(new IllegalArgumentException());
        }
        array.set(index,value);
        observers.get(0).showArray(array);
        return Result.createSuccess(value);
    }



}
