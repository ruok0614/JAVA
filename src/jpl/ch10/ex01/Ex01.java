package jpl.ch10.ex01;

public class Ex01 {
    public static String returnSpecialCharacter(String str) {
        String result = "";
        for (char c: str.toCharArray()) {
            if (c == '\n')
                result += "\\\"";
            else if (c == '\t')
                result += "\\t";
            else if (c == '\b')
                result += "\\b";
            else if (c == '\r')
                result += "\\r";
            else if (c == '\\')
                result += "\\\\";
            else if (c == '\'')
                result += "\\\'";
            else if (c == '\"')
                result += "\\\"";
            else
                result += "\\" + Integer.toOctalString(Character.getNumericValue(c));
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
