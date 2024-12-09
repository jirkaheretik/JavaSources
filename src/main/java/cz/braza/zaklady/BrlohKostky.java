package cz.braza.zaklady;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrlohKostky {
    public static void main(String[] args) {
        Set<String> moznosti = new HashSet<>();
        moznosti.add("T"); // 1x10
        for (int a = 1; a < 10; a++) {
            for (int b = 1; b <= 10 - a; b++) {
                if (a + b == 10) moznosti.add("" + a + b);
                else for (int c = 1; c <= 10 - a - b; c++) {
                    if (a + b + c == 10) moznosti.add("" + a + b + c);
                    else for (int d = 1; d <= 10 - a - b - c; d++) {
                        if (a + b + c + d == 10) moznosti.add("" + a + b + c + d);
                        else for (int e = 1; e <= 10 - a - b - c - d; e++) {
                            if (a + b + c + d + e == 10) moznosti.add("" + a + b + c + d + e);
                            else for (int f = 1; f <= 10 - a - b - c - d - e; f++) {
                                if (a + b + c + d + e + f == 10) moznosti.add("" + a + b + c + d + e + f);
                                else for (int g = 1; g <= 10 - a - b - c - d - e - f; g++) {
                                    if (a + b + c + d + e + f + g == 10) moznosti.add("" + a + b + c + d + e + f + g);
                                    else for (int h = 1; h <= 10 - a - b - c - d - e - f - g; h++) {
                                        if (a + b + c + d + e + f + g + h == 10) moznosti.add("" + a + b + c + d + e + f + g + h);
                                        else for (int i = 1; i <= 10 - a - b - c - d - e - f - g - h; i++) {
                                            if (a + b + c + d + e + f + g + h + i == 10) moznosti.add("" + a + b + c + d + e + f + g + h + i);
                                            else {
                                                int j = 10 - a - b - c - d - e - f - g - h - i;
                                                if (j > 0) moznosti.add("" + a + b + c + d + e + f + g + h + i + j);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Celkem je " + moznosti.size() + " variant.");
        for (String s : moznosti)
            System.out.println(s);
        System.out.println("Celkem je " + moznosti.size() + " variant.");
        List<String> max2 = moznosti.stream()
                .filter(a -> !a.contains("3"))
                .filter(a -> !a.contains("4"))
                .filter(a -> !a.contains("5"))
                .filter(a -> !a.contains("6"))
                .filter(a -> !a.contains("7"))
                .filter(a -> !a.contains("8"))
                .filter(a -> !a.contains("9"))
                .toList();
        System.out.println("Dvojkové: " + max2.size());
        System.out.println(max2.toString());
        for (int i = 9; i > 4; i--) {
            final int x = i;
            List<String> mezi = moznosti.stream().filter(a -> a.contains("" + x)).toList();
            System.out.printf("Pro velikost největšího sloupečku %d je %d možností.%n%s%n", i, mezi.size(), mezi.toString());
        }
    }
}
