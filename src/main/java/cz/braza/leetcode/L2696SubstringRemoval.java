package cz.braza.leetcode;

public class L2696SubstringRemoval {
    public static int minLength(String s) {
        int diff = 0;
        int oldLength;
        do {
            oldLength = s.length();
            s = s.replace("AB", "");
            s = s.replace("CD", "");
            diff = oldLength - s.length();
        } while (diff > 0);
        return s.length();
    }

    /**
     * Just a tad bit faster than working just with strings
     * @param s
     * @return
     */
    public static int minLengthSB(String s) {
        int diff = 0;
        int oldLength;
        StringBuilder sb = new StringBuilder(s);
        do {
            oldLength = sb.length();
            int pos = sb.lastIndexOf("AB");
            while (pos >= 0){
                sb.delete(pos, pos + 2);
                pos = sb.lastIndexOf("AB");
            }
            System.out.println(sb);
            pos = sb.lastIndexOf("CD");
            while (pos >= 0){
                sb.delete(pos, pos + 2);
                pos = sb.lastIndexOf("CD");
            }
            System.out.println(sb);
            diff = oldLength - sb.length();
            System.out.println(diff);
        } while (diff > 0);
        return sb.length();
    }

    private static void test(String s) {
        System.out.println(s + ", res: " + minLengthSB(s));
    }

    public static void main(String[] args) {
        test("ABFCACDB");
    }
}
