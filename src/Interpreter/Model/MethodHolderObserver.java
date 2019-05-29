package Interpreter.model;

import java.lang.reflect.Method;
import java.util.List;

public interface MethodHolderObserver {
    void showMethodList(List<Method> methodlist);
}
