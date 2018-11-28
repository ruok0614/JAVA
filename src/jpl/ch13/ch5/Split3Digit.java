package jpl.ch13.ch5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Split3Digit {

    static int countCharactor(String str, char ch) {
        int count = 0;
        int idx = -1;
        do {
            idx = str.indexOf(ch, idx + 1);
            count++;
        } while (idx != -1);
        return count - 1;
    }

    static int countString(String str, String sStr) {
        int count = 0;
        int idx = -1;
        do {
            idx = str.indexOf(sStr, idx + 1);
            count++;
        } while (idx != -1);
        return count - 1;
    }
    public static String[] delimitedString(String from, char start, char end) {
        int startPos = from.indexOf(start);
        int endPos = from.lastIndexOf(end);
        List<String> strs = new ArrayList<String>();
        if (startPos == -1)
            return null;
        else if (endPos == -1) {
           strs.add(from.substring(startPos));
            return strs.toArray(new String[strs.size()]);
        } else if (startPos > endPos)
            return null;
        else {
            for (int startIdx = startPos;
                 startIdx != -1;
                 startIdx = from.indexOf(start, startIdx + 1)) {
                int endIdx = from.indexOf(end, startIdx);
                while (endIdx != -1) {
                    String str = from.substring(startIdx, endIdx + 1);
                    if (!strs.contains(str))
                        strs.add(str);
                    endIdx = from.indexOf(end, endIdx + 1);
                }
            }
            return strs.toArray(new String[strs.size()]);
        }
    }

   public static String insertDelimiter(String str) {
        StringBuffer buf = new StringBuffer();
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            StringBuffer buff = new StringBuffer(matcher.group());
            for (int i = buff.length() - 3; i > 0; i -= 3)
                buff.insert(i, ",");
            matcher.appendReplacement(buf, buff.toString());
        }
        matcher.appendTail(buf);
        return buf.toString();
    }

    public static void main(String[] args){
        System.out.println(Split3Digit.insertDelimiter("A4159876123"));
    }
}
