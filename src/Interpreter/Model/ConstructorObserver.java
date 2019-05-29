package Interpreter.model;

import java.lang.reflect.Constructor;

public interface ConstructorObserver {
    void showConstructor(Constructor[] constructorlist);
    void showSetFieldProperty(Constructor constructor);
}
