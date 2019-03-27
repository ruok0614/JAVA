package InterPrinter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

/**
 * インスタンス化した情報を統括するクラス
 */
public class FieldHolder {
    private Member[] constructorList;
    private Object selectField ;

    /**
     *
     * @param constructorList
     */
    public void setConstructorList(Member[] constructorList) {
        this.constructorList = constructorList;
    }
    public 1 setSelectField(String selectField) {
        try {
            String a = ( String ) Class.forName(selectField).getDeclaredConstructor().newInstance();
            return true;

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


}
