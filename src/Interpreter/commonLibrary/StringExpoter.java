package Interpreter.commonLibrary;

import Interpreter.model.ErrorMessage;

import javax.swing.*;

public class StringExpoter {
    public static Object toClassType(String args){

        // ""の場合はvoidを返す
        if(args.length() == 0){
            return null ;
        }

        // ''で囲まている場合はcharで返す
        if(checkEnclose(args,'\'')){
            if(args.length() == 3)
                return fetch(args).charAt(0);
            else{
                throw new IllegalArgumentException();
            }
        }
        // ""の場合は文字列で返す
        if(checkEnclose(args,'\"')){
            return fetch(args);
        }
        // 数字に変換できればintできなければ文字列で返す
        else{
            if(args.equals("true")){
                return Boolean.TRUE;
            }else if(args.equals("false")){
                return Boolean.FALSE;
            }
            try {
                return Integer.parseInt(args);
            }catch(Error e){
                return args;
            }
        }
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
        int len = str.length();
        return str.substring(1,len);
    }
}
