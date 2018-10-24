package jpl.ch10.Ex05;

/**
 * @author mary-mogreen
 *
 */
public class SubString {

    private String string;

    public SubString(String string) {
        this.string = string;
    }

    /**
     * 引数で渡された2つのcharの間の文字列を返す。
     * charが存在しない場合は、元の文字列を返す。
     * @param startChar
     * @param endChar
     * @return
     */
    public String getBetween(char startChar, char endChar) {
        String str = "";
        boolean flag = false;
        for (char c: string.toCharArray()) {
            str = flag ? str + c : str + "";
            if (c == startChar) flag = true ;
            if (c == endChar && flag)
                return str.substring(0, str.length() - 1);
        }
        return string;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        SubString ss = new SubString("abcdefghijk");

        System.out.println(ss.getBetween('c', 'j'));

        SubString sss = new SubString("aebcfdfdeeeejk");
        System.out.println(sss.getBetween('c', 'e'));

        System.out.println(sss.getBetween('z', 'x'));

    }

}