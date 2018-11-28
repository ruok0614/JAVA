package jpl.ch13.ex3;

import java.util.ArrayList;
import java.util.List;

public class DelimitedString {

    public static List<String> delimitedString(String form, char start, char end){
        List<String> res = new ArrayList<String>();
        int startPos = form.indexOf(start);
        int endPos = form.indexOf(end);
        while (true) {
            if (startPos == -1)
                break;
            else if (endPos == -1)
                break;
            else if (startPos > endPos) {
                startPos = form.indexOf(start, startPos + 1);
                endPos = form.indexOf(end, startPos + 1);
                continue;
            }else
                res.add(form.substring(startPos, endPos + 1));
                System.out.println(form.substring(startPos, endPos + 1));
                startPos = form.indexOf(start,startPos + 1);
                endPos = form.indexOf(end,endPos + 1);
        }
        return res;
    }

    static public void main(String[] args){
        System.out.println(DelimitedString.delimitedString("aaa<sss>ccc<aasad>kdsa>><<sdasda<c>",'<','>'));
    }
}
