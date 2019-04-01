package Interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
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

    public void serchConstructor(String inputText){
        Class<?> clazz = null;
        try{
            clazz = Class.forName(inputText);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        constructorlist = clazz.getConstructors();
        observers.get(0).showConstructor(constructorlist);
    }

    public void selectedConstructor(int indexNum){
        Constructor claszz = constructorlist[indexNum];
        observers.get(0).showSetFieldProperty(claszz);
        objectHolder.newInstance(claszz);
    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(ConstructorObserver observer){
        this.observers.add(observer);
    }



}
