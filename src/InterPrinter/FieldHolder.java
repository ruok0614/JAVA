package InterPrinter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

/**
 * インスタンス化した情報を統括するクラス
 */
public class FieldHolder {

    private Object selectField ;


    public Object selectConstructor(String selectConstructor) {
        try {
            Object a =  Class.forName(selectConstructor).getDeclaredConstructor().newInstance();
            return true;

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


}
