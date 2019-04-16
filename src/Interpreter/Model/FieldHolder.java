package Interpreter.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldHolder {
    List<Field> fieldlist;

    public List<FieldHolderObserver> getObservers() {
        return observers;
    }

    private List<FieldHolderObserver> observers;
    public FieldHolder(){
        observers = new ArrayList<FieldHolderObserver>();
    }

    public void addFieldList(Object obj){
        try {
            fieldlist = getFieldFromClass(obj.getClass());
            observers.get(0).showFieldList(fieldlist);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public List<Field> getFieldFromClass(Class clazz)
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

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(FieldHolderObserver observer){
        this.observers.add(observer);
    }

}
