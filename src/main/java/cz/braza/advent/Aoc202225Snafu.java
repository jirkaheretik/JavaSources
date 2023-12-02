package cz.braza.advent;

import java.io.File;
import java.util.Scanner;

/**
 * Advent of Code 2022, day 25. Basically base-5 computing with a little "twist".
 * Instead of digits 0, 1, 2, 3 and 4 we do have = (as -2), - (as -1), 0, 1 and 2.
 * Converting values back and forth is more than enough to solve the riddle.
 * Also, since there are values of at least 20 digits (2*10^13), we need long arithmetics.
 * (as int is enough for at most 14 digits in SNAFU)
 */

/*
INPUT:
100=
110
1==2-
11=
12-0-0-=
1==01==2
111-2=2==-21
10-=-022
21=2=2-0=
22
11=01=0011-00-2
1=0-12
1=-2
12120=20=-120-=100
1-220-10120--=01=
10=000=-122--0=
1-001--2200
201-0-1--=2
1202-1
102101-0-2=0=0=1=2
120=0
2=--1=0012-=
100=12100220220
1-11211
1201=---
1=1=-
11==0200=
1-1--120220000
200==
1==-02-1=2-
1=-2=
1=-1==22-11--=1
12=1120=10=-1=10
2021=--12012-
122=0-122=02-2=
220====0--
1-1=1011-022=-=01
11=200-0100
2=-1==1=0100
100-2012-=10-00-21
210122011
1===210-=110-
1002201==0-
1--1211-1==0==-0
20==02-2120
1==
12=22201-=0
100101
12120-=002
10-0=--=220=-000
1=-1012=22=2-20-101
1==0=-20220-==
1=0
1=20110-==02000
2-11200
1=-0012
12-1121=2112=2-
1=-=2-21-02-==0-2-
1-=1==-=
20==
1220
21010==-=000-01
1-20=-==-0==-1=01=1=
2=22101
1-=12-0-11-102
2-0=11=2-=0=2-0
11=2=1-1020--0=
122102-1222
1-0020==0=-2-1=12
1=020-=0-
1-
10=-==1=-=-=22
20-12==0=-=-12
11111==
2=2--11-12
1=2--
11=00-10220-=
1=01--0
1-0
10=12==122
2-020
1=12=1=-222002-
11
202=-===0==01
1-0221=211
21100
10-2-==01
11=020-=10-1110-11=
1--0
1-0-12=0=2
1-1212012--=1-20--
1=-121200020100
2-=0-=-21-10-
221
1=0-=-
11-2-
1=-=200
210=1020-11022=1=0
110==--2-1-121==2=1
1200
20-==1=0-12
11=02=2
1-=2
22-0201=-2=-
1=0201122==2002-1
1-1=21002=1
1-=20=12-21121
12102
1--=211200
20=-===2-=1-1-1
2=0-1022=21---2
2=1
1-01111=11-
10210102=0121
11002=-1-1202=21=

 */
public class Aoc202225Snafu {
    public static final int[] CIFRY = {1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    public static long[] CIF2;
    public static final int MAXDIGITS = 24;
    public static final int BASE = 5;

    static {
        System.out.println("Filling out CIFRY...");
        CIF2 = new long[MAXDIGITS];
        CIF2[0] = 1;
        for (int i = 1; i < CIF2.length; i++) {
            CIF2[i] = BASE * CIF2[i - 1];
        }
        System.out.println("Done. Last value: " + CIF2[MAXDIGITS - 1]);
    }
    public static long snafu2dec(String snafu) {
        long result = 0;
        int delka = snafu.length();
        for (int i = 0; i < delka; i++) {
            char c = snafu.charAt(delka - i - 1);
            int val = switch (c) {
                case '=' -> -2;
                case '-' -> -1;
                default -> Integer.parseInt("" + c);
            };
            result += CIF2[i] * val;
        }
        return result;
    }

    public static String dec2snafu(long vstup) {
        System.out.println("Calling dec2snafu(" + vstup  + ")");
        String result = "";
        int overflow = 0;
        while (vstup != 0 || overflow != 0) {
            int modulo = (int)((vstup + overflow) % BASE);
            vstup = (vstup + overflow) / BASE;
            overflow = 0;
            if (modulo > 2) {
                overflow = 1;
                result = (modulo == 3 ? "=" : "-") + result;
/*            } else if (modulo < 0) {   // this part is only needed for processing NEGATIVE numbers
                if (modulo > -3) {
                    result = (modulo == -2 ? "=" : "-") + result;
                } else {
                    overflow = -1;
                    result = "" + (modulo + 5) + result;
                }*/
            } else {
                result = "" + modulo + result;
            }
        }
        return result;
    }

    public static void test() {
        System.out.println("========== TESTING: ==============");
        String[] vstup = {"1=11-2", "1=110=", "1121-1110-1=0", "2022", "222======100"};
        int suma = 0;
        for (String snafu: vstup)
            suma += snafu2dec(snafu);
        System.out.println(suma + ", snafu: " + dec2snafu(suma));
        System.out.println("1121-1110-1=0" + ", dec: " + snafu2dec("1121-1110-1=0") + " and back: " + dec2snafu(snafu2dec("1121-1110-1=0")));
        System.out.println("===--2--===" + ", dec: " + snafu2dec("===--2--===") + " and back: " + dec2snafu(snafu2dec("===--2--===")));
    }
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc22_25.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long total = 0;
        while (vstup.hasNextLine()) {
            total += snafu2dec(vstup.nextLine());
        }
        System.out.println(dec2snafu(total));
        // test();
    }
}
