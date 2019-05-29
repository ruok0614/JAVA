package Interpreter.model;

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

    public ArrayHolder getArrayHolder(){return arrayHolder;}

    private ObjectHolder objectHolder;
    private MethodHolder methodHolder;
    private FieldHolder fieldHolder;
    private ArrayHolder arrayHolder;

    public Context() {
        this.fieldHolder = new FieldHolder();
        this.methodHolder = new MethodHolder();
        this.arrayHolder = new ArrayHolder();
        this.objectHolder = new ObjectHolder(fieldHolder,methodHolder,arrayHolder);
        this.constructorHolder = new ConstructorHolder(objectHolder);
    }



}
