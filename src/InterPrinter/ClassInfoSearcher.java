package InterPrinter;

import java.lang.reflect.Member;

public class ClassInfoSearcher {
    public static Member[] serchConstructor(String inputText){
        Class<?> clazz = null;
        Object ins = null;
        try{
            clazz = Class.forName(inputText);

        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Member[] mem = clazz.getConstructors();

        return mem;
    }
    public static void main(String args[]) throws ClassNotFoundException {

    }
}
