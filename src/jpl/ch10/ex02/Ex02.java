package jpl.ch10.ex02;

import jpl.ch10.ex01.Ex01;

public class Ex02 {
    public static String returnSpecialCharacter(String str) {
        String result = "";
        for (char c: str.toCharArray()) {
            switch(c){
                case ('\n'):
                    result += "\\\"";
                case '\t':
                    result += "\\t";
                case '\b':
                    result += "\\b";
                case '\r':
                    result += "\\r";
                case '\\':
                    result += "\\\\";
                case '\'':
                    result += "\\\'";
                case '\"':
                    result += "\\\"";
                default:
                    result += "\\" + Integer.toOctalString(Character.getNumericValue(c));
            }
        }
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "aiueo\t\najidjigjr\"\'\rds\b";
        String str2 = Ex01.returnSpecialCharacter(str);
        System.out.println("str: " + str + "=> SpecialCharacter: " + str2);
    }
}
