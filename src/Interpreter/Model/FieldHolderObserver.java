package Interpreter.model;

import java.lang.reflect.Field;
import java.util.List;

public interface FieldHolderObserver {
    void showFieldList(List<Field> fieldlist);
}
