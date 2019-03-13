package InterPrinter;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class Controller {
    IObserver observer;
    ArrayList<FieldHolder> fieldHolderList = new ArrayList<>();
    public void pushedclassNameGetButton(String inputText){
        Member[] mem = ClassInfoSearcher.serchConstructor(inputText);
        FieldHolder fieldHolder = new FieldHolder();
        fieldHolder.setConstructorList(mem);
        fieldHolderList.add(fieldHolder);
        observer.showConstructor(mem);
    }
    public void addObserver(IObserver observer){
        this.observer = observer;
    }
}
