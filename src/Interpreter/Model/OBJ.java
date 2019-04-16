package Interpreter.Model;

public class OBJ {
    private String name;
    private Object obj;
    public OBJ(String name, Object obj){
        this.name = name;
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public Object getObj() {
        return obj;
    }
}
