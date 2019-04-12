package Interpreter.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

public interface ConstructorObserver {
    void showConstructor(Constructor[] constructorlist);
    void showSetFieldProperty(Constructor constructor);
}
