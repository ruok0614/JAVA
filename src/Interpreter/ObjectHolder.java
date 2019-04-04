package Interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

public class ObjectHolder {
    FieldHolder fieldHolder;
    MethodHolder methodHolder;
    ObjectHolder(FieldHolder fieldHolder, MethodHolder methodHolder){
        this.fieldHolder = fieldHolder;
        this.methodHolder = methodHolder;
    }

    public void newInstance(Constructor constructor,String objName, Object[] args){
        try {
            Object a = constructor.newInstance(args);
            System.out.println(a);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
