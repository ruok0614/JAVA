package Interpreter.model;

import java.util.List;

public interface ObjectHolderObserver {
    void showObjectList(List<OBJ> obj);

    void showInvokeResult(Object returnValue);
}
