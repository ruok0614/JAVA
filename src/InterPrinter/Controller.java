package InterPrinter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewとModelの中継役
 */
public class Controller {

    private FieldHolder fieldHolder;
    private ConstructorHolder constructorHolder;

    Controller(IConstructorObserver observer){
        constructorHolder = new ConstructorHolder(observer);

    }


    public void selectedValue(String value) {
        Object a = fieldHolder.selectConstructor(value);
    }
    public void pushedclassNameGetButton(String inputText){
        constructorHolder.serchConstructor(inputText);
    }



}
