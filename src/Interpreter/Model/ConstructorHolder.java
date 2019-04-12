package Interpreter.Model;

import java.lang.reflect.Constructor;
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

    public void searchConstructor(String inputText){
        Class<?> clazz = null;
        try{
            clazz = Class.forName(inputText);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        constructorlist = clazz.getConstructors();
        observers.get(0).showConstructor(constructorlist);
    }

    public void newInstance(int indexNum, Object args){
        Constructor claszz = constructorlist[indexNum];
        observers.get(0).showSetFieldProperty(claszz);
        objectHolder.createObject(claszz,"aa", args);
    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(ConstructorObserver observer){
        this.observers.add(observer);
    }



}
