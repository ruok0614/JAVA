package Interpreter.model;

import Interpreter.model.common.Result;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldHolder {
    private List<Field> fieldlist;
    private Object selectObj;

    public List<FieldHolderObserver> getObservers() {
        return observers;
    }

    private List<FieldHolderObserver> observers;
    public FieldHolder(){
        observers = new ArrayList<FieldHolderObserver>();
    }

    // 名前考える
    public void addFieldList(Object obj){
        try {
            selectObj = obj;
            fieldlist = getFieldFromClass(selectObj.getClass());
            observers.get(0).showFieldList(fieldlist);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            observers.get(0).showFieldList(null);
        }
    }

    private List<Field> getFieldFromClass(Class clazz)
            throws NoSuchFieldException {
        List<Field> field = new ArrayList<>();
        while (clazz != null) {
            for (Field f:clazz.getDeclaredFields()){
                field.add(f);
            }
            clazz = clazz.getSuperclass();
        }

        if (field == null) {
            throw new NoSuchFieldException();
        }
        return field;
    }

    public Object getFieldValueFromIndex(int index){
        Field field = fieldlist.get(index);
        field.setAccessible(true);
        try {
            return field.get(selectObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getFieldValue(Field field){
        field.setAccessible(true);
        try {
            return field.get(selectObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Result setField(int index, Object value, Object target) throws IllegalAccessException {

        Field f = fieldlist.get(index);
        f.setAccessible(true);


        if(value.getClass().isArray()) {
            String name = value.getClass().getComponentType().getSimpleName();
            Class clazz = String.class;
            int length = Array.getLength(value);
            try {
                switch (name) {
                    case "Integer":
                        int[] intArray = (int[]) Array.newInstance(int.class, length);
                        for (int i = 0; i < length; i++) {
                            intArray[i] = (int) Array.get(value, i);
                        }
                        f.set(target, intArray);
                        break;
                    case "Character":
                        char[] charArray = (char[]) Array.newInstance(char.class, length);
                        for (int i = 0; i < length; i++) {
                            charArray[i] = (char) Array.get(value, i);
                        }
                        f.set(target, charArray);
                        break;
                    case "Boolean":
                        boolean[] boolArray = (boolean[]) Array.newInstance(boolean.class, length);
                        for (int i = 0; i < length; i++) {
                            boolArray[i] = (boolean) Array.get(value, i);
                        }
                        f.set(target, boolArray);
                        break;
                    case "Byte":
                        byte[] byteArray = (byte[]) Array.newInstance(byte.class, length);
                        for (int i = 0; i < length; i++) {
                            byteArray[i] = (byte) Array.get(value, i);
                        }
                        f.set(target, byteArray);
                        break;
                    default:
                        f.set(target, value);
                }
            }catch (IllegalArgumentException e){
                observers.get(0).showFieldList(fieldlist);
                return Result.createFailure(e);
            }
            observers.get(0).showFieldList(fieldlist);
            return Result.createSuccess(value);
        }
        try {
            f.set(target, value);
        }catch (IllegalArgumentException e){
            observers.get(0).showFieldList(fieldlist);
            return Result.createFailure(e);
        }
        observers.get(0).showFieldList(fieldlist);
        return Result.createSuccess(value);
    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(FieldHolderObserver observer){
        this.observers.add(observer);
    }

    private <T> T[] newArray(Class<T> c,Object obj) {
        int length = Array.getLength(obj);
        T[] array = (T[]) Array.newInstance(c, length);
        for(int i = 0; i < length; i++){
            array[i] = (T)Array.get(obj,i);
        }
        return array;
    }

}
