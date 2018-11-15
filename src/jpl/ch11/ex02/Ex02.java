package jpl.ch11.ex02;

public class Ex02<T> {

    private final String name;
    private T value = null;


    public Ex02(String name) {
        this.name = name;
    }

    public Ex02(String name, T value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public T setValue(T newValue) {
        T oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public String toString() {
        return name + "='" + value + "'";
    }
}
