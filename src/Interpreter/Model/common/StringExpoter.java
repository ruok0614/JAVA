package Interpreter.model.common;

import java.util.ArrayList;
import java.util.List;

public class StringExpoter {
    public static Object[] toClassType(String args){

        // ""の場合はnullを返す
        if(args.length() == 0){
            return new Object[0] ;
        }
        if(args.equals("null")){
            return null;
        }

        String[] splitArgs = args.split(",");
        List<Object> objArgs = new ArrayList<>();
        for(String s : splitArgs) {
            // ''で囲まている場合はcharで返す
            if (checkEnclose(s, '\'')) {
                if (s.length() == 3)
                    objArgs.add(fetch(s).charAt(0));
                else {
                    throw new IllegalArgumentException();
                }
            }
            // ""の場合は文字列で返す
            if (checkEnclose(s, '\"')) {
                objArgs.add(fetch(s));
            }
            // 数字に変換できればintできなければ文字列で返す
            else {
                if (s.equals("true")) {
                    objArgs.add(Boolean.TRUE);
                } else if (s.equals("false")) {
                    objArgs.add(Boolean.FALSE);
                }
                try {
                    objArgs.add(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return objArgs.toArray();
    }

    private static boolean checkEnclose(String args, char target){
        int len = args.length() - 1;
        if(args.charAt(0) == target){
            if(args.charAt(len) == target){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 文字列の最初の文字と最後の文字を削除したものを返します．
     * @param str 最初と最後以外を取り出したい文字列
     * @return 最初と最後以外を取り出した文字列
     */
    private static String fetch(String str){
        int len = str.length() - 1;
        return str.substring(1,len);
    }
}
