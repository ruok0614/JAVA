package Interpreter.model;

import Interpreter.model.common.Result;

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

}
