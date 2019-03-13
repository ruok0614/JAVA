package InterPrinter;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class InstanceGenerator {


}


/**
 * リフレクションをサポートするメソッドをまとめたクラス
 * Copyright 2013 Kosuke NAMIHIRA All Rights Reserved.
 *
 * @author Kohsuke NAMIHIRA
 */
class ReflectionUtils {

    private static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap<String, Class<?>>();

    static {
        registerPrimitiveType("byte", byte.class);
        registerPrimitiveType("short", short.class);
        registerPrimitiveType("int", int.class);
        registerPrimitiveType("long", long.class);
        registerPrimitiveType("boolean", boolean.class);
        registerPrimitiveType("float", float.class);
        registerPrimitiveType("double", double.class);
        registerPrimitiveType("char", char.class);
    }

    private ReflectionUtils() {
    }

    private static void registerPrimitiveType(String typeName,
                                              Class<?> clazz) {
        PRIMITIVE_TYPE_MAP.put(typeName, clazz);
    }

    /**
     * クラス名から、型を取得します。Primitiveな型に対しても、そのClassオブジェクトを返却します。<br/>
     * 例："int" -> int.class
     * @param className
     *            型の取得対象となるクラス名
     * @return オブジェクトの型
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getType(String className) throws ClassNotFoundException{
        if (PRIMITIVE_TYPE_MAP.containsKey(className)) {
            return (Class<T>) PRIMITIVE_TYPE_MAP.get(className);
        }
        return (Class<T>) Class.forName(className);
    }
}