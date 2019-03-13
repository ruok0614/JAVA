package InterPrinter;

import java.lang.reflect.Member;

/**
 * インスタンス化した情報を統括するクラス
 */
public class FieldHolder {
    Member[] constructorList;

    /**
     *
     * @param constructorList
     */
    public void setConstructorList(Member[] constructorList) {
        this.constructorList = constructorList;
    }
}
