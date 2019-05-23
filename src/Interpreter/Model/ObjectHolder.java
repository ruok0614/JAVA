package Interpreter.Model;

import java.lang.reflect.Array;
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

    /**
     * オブジェクトを生成してOBJに生成したオブジェクトを追加します．
     * @param constructor　コンストラクター
     * @param objName　変数名
     * @param args　引数
     */
    public void createObject(Constructor constructor, String objName, Object args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object obj = constructor.newInstance(args);
        addOBJ(new OBJ(objName,obj));
        observers.get(0).showObjectList(objectList);
    }

    public void addObserver(ObjectHolderObserver observer){
        observers.add(observer);
    }

    public void addOBJ(OBJ obj){
        objectList.add(obj);
    }

    public void showFieldAndMethod(int index){
        Object obj = objectList.get(index).getValue();
        methodHolder.addMethodList(obj);
        fieldHolder.addFieldList(obj);
    }

    public void addArray(String args, int arrayNum, String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        clazz = Class.forName(args);
        Object arr = Array.newInstance(clazz, arrayNum);
        addOBJ(new OBJ(name,arr));
        observers.get(0).showObjectList(objectList);
    }
}
