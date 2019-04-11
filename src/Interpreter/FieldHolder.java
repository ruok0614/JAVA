package Interpreter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldHolder {
    Field[] fieldlist;
    private List<FieldHolderObserver> observers;
    public FieldHolder(){
        observers = new ArrayList<FieldHolderObserver>();
    }
    public void addFieldList(Object obj){
        fieldlist = obj.getClass().getFields();
        observers.get(0).showFieldList(fieldlist);
        for (Field m:fieldlist) {
            System.out.println(m);
        }

    }

    /**
     * オブザーバーを追加する
     * @param observer
     */
    public void addObserver(FieldHolderObserver observer){
        this.observers.add(observer);
    }

}
