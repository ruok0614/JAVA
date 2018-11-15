package jpl.ch12.ex01;

public class ObjectNotFoundException extends Exception {



    Object obj = null;



    public ObjectNotFoundException(Object obj) {

        super("Object is not found. object: " + obj);

        this.obj = obj;

    }



}