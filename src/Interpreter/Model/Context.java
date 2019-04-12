package Interpreter.Model;

public class Context {
    private ConstructorHolder constructorHolder;

    public ConstructorHolder getConstructorHolder() {
        return constructorHolder;
    }

    public ObjectHolder getObjectHolder() {
        return objectHolder;
    }

    public MethodHolder getMethodHolder() {
        return methodHolder;
    }

    public FieldHolder getFieldHolder() {
        return fieldHolder;
    }

    private ObjectHolder objectHolder;
    private MethodHolder methodHolder;
    private FieldHolder fieldHolder;

    public Context() {
        this.fieldHolder = new FieldHolder();
        this.methodHolder = new MethodHolder();
        this.objectHolder = new ObjectHolder(fieldHolder,methodHolder);
        this.constructorHolder = new ConstructorHolder(objectHolder);
    }



}
