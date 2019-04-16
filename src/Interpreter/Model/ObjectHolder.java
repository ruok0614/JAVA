package Interpreter.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class ObjectHolder {
    FieldHolder fieldHolder;
    MethodHolder methodHolder;
    List<ObjectHolderObserver> observers;
    List<OBJ> objectList;
    ObjectHolder(FieldHolder fieldHolder, MethodHolder methodHolder){
        this.fieldHolder = fieldHolder;
        this.methodHolder = methodHolder;
        observers = new ArrayList<ObjectHolderObserver>();
        objectList = new ArrayList<OBJ>();
    }

    public void createObject(Constructor constructor, String objName, Object args){
        try {
            Object obj = constructor.newInstance(args);
            addOBJ(new OBJ(objName,obj));
            observers.get(0).showObjectList(objectList);


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void addObserver(ObjectHolderObserver observer){
        observers.add(observer);
    }

    public void addOBJ(OBJ obj){
        objectList.add(obj);
    }

    public void showFieldAndMethod(int index){
        Object obj = objectList.get(index).getObj();
        methodHolder.addMethodList(obj);
        fieldHolder.addFieldList(obj);
    }
}
