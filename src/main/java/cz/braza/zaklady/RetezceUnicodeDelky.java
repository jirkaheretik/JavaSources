package cz.braza.zaklady;

public class RetezceUnicodeDelky {
    public static void main(String[] args) {
        String[] TEST = {"ahoj babi", ":-)", "\uD83E\uDD23", "příliš", "ďáblíček", "\uD83C\uDF89", "\uD83D\uDC6F", "\uD83D\uDC68\u200D❤\uFE0F\u200D\uD83D\uDC8B\u200D\uD83D\uDC68"};
        for (String t: TEST)
            System.out.println("'" + t + "' --- " + t.length());
    }
}
