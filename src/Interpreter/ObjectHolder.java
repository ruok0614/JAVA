package Interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ObjectHolder {
    FieldHolder fieldHolder;
    MethodHolder methodHolder;
    ObjectHolder(FieldHolder fieldHolder, MethodHolder methodHolder){
        this.fieldHolder = fieldHolder;
        this.methodHolder = methodHolder;
    }

    public void createObject(Constructor constructor, String objName, Object args){
        try {
            Object obj = constructor.newInstance(args);
            methodHolder.addMethodList(obj);
            fieldHolder.addFieldList(obj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
