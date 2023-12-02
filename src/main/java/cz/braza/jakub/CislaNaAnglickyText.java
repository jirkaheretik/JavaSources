package cz.braza.jakub;
public class CislaNaAnglickyText {
    public static String[] RADY = { "", "thousand ", "million ", "billion "};
    public static String[] JEDNOTKY = {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ", "eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ", "nineteen "};
    public static String[] DESITKY = {"", "ten ", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};

    public static String sub1000ToEng(int value, int rad) {
        int jednotky = value % 10;
        value /= 10;
        int desitky = value % 10;
        value /= 10;
        int stovky = value;
        return JEDNOTKY[stovky] + (stovky > 0 ? "hundred " : "") +
                (desitky >= 2 ? DESITKY[desitky] + JEDNOTKY[jednotky] : JEDNOTKY[10*desitky + jednotky])
                + RADY[rad];
    }

    public static String intToEng(int value) {
        String tmpResult = value + " = ";
        String result = "";
        int rady = 0;
        while (value > 0) {
            result = sub1000ToEng(value % 1000, rady++) + result;
            value /= 1000;
        }
        return tmpResult + result;
    }
    public static void main(String[] args) {
        System.out.println(intToEng(314));
        System.out.println(intToEng(2040924666));
        System.out.println(intToEng(1327));
        System.out.println(intToEng(301327));
        System.out.println(intToEng(21327));

        System.out.println(intToEng(106));


    }
}
