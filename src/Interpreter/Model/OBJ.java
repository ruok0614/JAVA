package Interpreter.model;

public class OBJ {
    private String name;
    private Object value;

    public OBJ(String name, Object value){
        this.name = name;
        this.value = value;
    }

    public Class<?> getValueType(){
        return value.getClass();
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
