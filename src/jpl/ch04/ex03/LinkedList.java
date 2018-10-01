package jpl.ch04.ex03;


public interface LinkedList<T>{
    String toString();
    void setObj(Object obj);
    void setNextList(T next);
    void show();
    int getLength();

}