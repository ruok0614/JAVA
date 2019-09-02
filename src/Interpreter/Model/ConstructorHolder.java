package Interpreter.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ConstructorHolder {
    Constructor[] constructorlist;
    private ObjectHolder objectHolder;
    private List<ConstructorObserver> observers;

    public ConstructorHolder(ObjectHolder objectHolder) {
        this.objectHolder = objectHolder;
        observers = new ArrayList<ConstructorObserver>();
    }

    public void searchConstructor(String inputText) throws ClassNotFoundException {
        Class<?> clazz = null;
        clazz = Class.forName(inputText);
        constructorlist = clazz.getConstructors();
        observers.get(0).showConstructor(constructorlist);
    }

    public void newInstance(int indexNum, Object[] args,String name) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor claszz = constructorlist[indexNum];
        observers.get(0).showSetFieldProperty(claszz);
        objectHolder.createObject(claszz,name, args);
    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(ConstructorObserver observer){
        this.observers.add(observer);
    }



}
