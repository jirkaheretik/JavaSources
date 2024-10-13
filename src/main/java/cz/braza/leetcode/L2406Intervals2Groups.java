package cz.braza.leetcode;

import java.util.*;

public class L2406Intervals2Groups {
    /*
    Chybný výpočet - maximální počet navzájem se protínajících intervalů je chybně
    oběma směry. Pokud se nic neprotíná, vrací nulu, namísto 1, naopak někdy je
    počet protnutí s dalšími intervaly větší než správná odpověď, protože se intervaly
    mohou protínat v různých místech
     */
    public static int minGroupsNOOOO(int[][] intervals) {
        // Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int maxIntersectionCount = 0;
        for (int idx = 0; idx < intervals.length; idx++) {
            int intersectionCount = 0;
            for (int other = 0; other < intervals.length; other++) {
                if (idx == other) continue;
                if (intervals[idx][0] <= intervals[other][1] && intervals[idx][1] >= intervals[other][0])
                    intersectionCount++;
            }
            if (intersectionCount > maxIntersectionCount)
                maxIntersectionCount = intersectionCount;
        }
        return maxIntersectionCount;
    }

    /*
    Druhý pokus - nejprve projdeme intervaly a najdeme minimální a maximální hodnotu.
    Potom procházíme všechny tyto hodnoty a počítáme, do kolika spadají intervalů.
    Časová složitost zřejmě NxM (N je počet intervalů, M maximální hodnota)
    vede k TLE.
     */
    public static int minGroupsTLE(int[][] intervals) {
        int minValue = intervals[0][0];
        int maxValue = intervals[0][1];
        for (int[] interval: intervals) {
            if (interval[0] < minValue) minValue = interval[0];
            if (interval[1] > maxValue) maxValue = interval[1];
        }
        int maxIntersections = 0;
        for (int value = minValue; value < maxValue; value++) {
            int intersections = 0;
            for (int[] interval: intervals)
                if (interval[0] <= value && value <= interval[1])
                    intersections++;
            if (maxIntersections < intersections)
                maxIntersections = intersections;
        }
        return maxIntersections;
    }


    /*
    Třetí pokus, na první pohled velmi nadějný, ale vlastně "stejný".
    Uděláme pole všech hodnot, procházíme intervaly a všude v nich přičteme jedničku.
    Pak stačí toto pole projít a najít max hodnotu, což je ta, co spadá do
    nejvíce intervalů, a to je zároveň odpověď.
    Časová složitost zřejmě NxM (N je počet intervalů, M maximální hodnota)
    vede k TLE.
     */
    public static int minGroupsTLEAgain(int[][] intervals) {
        int[] intersections = new int[1000001];
        for (int[] interval: intervals) {
            for (int i = interval[0]; i <= interval[1]; i++)
                intersections[i]++;
        }
        int maxIntersections = 0;
        for (int val: intersections)
            if (val > maxIntersections)
                maxIntersections = val;
        return maxIntersections;
    }

    /*
    Ze Solutions - rozdělím na pole začátků a konců, každé z nich seřadím.
    Potom procházím jedno (např. začátky), a dokud jsou konce menší,
    posouvám se v koncích, jinak potřebuju další grupu.
    Neumím dohlédnout, proč to určitě funguje.
     */
    public static int minGroups(int[][] intervals) {
        int[] begins = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            begins[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(begins);
        Arrays.sort(ends);
        int endCount = 0;
        int maxIntersections = 0;
        for (int start: begins)
            if (start > ends[endCount])
                endCount++;
            else
                maxIntersections++;
        return maxIntersections;
    }

    /*
    Ze Solutions, ale aspoň ho zjevně chápu a není o tolik horší.
    Udělám si pole všech možných hodnot, nebo nejřív projdu intervaly a
    najdu minimum a maximum a udělám si jen tyhle příslušné hodnoty
    (nepotřebuji celý rozsah)
    Procházím intervaly, tam, kde začíná, si do pole přičtu +1 (začíná tam další),
    potom ZA tím, kde končí (tj. [end+1]) si *odečtu* 1 (tam už je zase volněji.
    Potom když takto zpracuju všechny intervaly, procházím to pole hodnot,
    a najdu-li kladné číslo (přibyl interval nebo více), přičtu si k počítadlu,
    příp. zkontroluju, jestli není větší než dosavadní maximum a zapamatuju si.
    Najdu-li zápornou hodnotu, upravím počítadlo, u nuly nemusím řešit nic.
     */
    public static int minGroupsOtherSolution(int[][] intervals) {
        int[] changes = new int[1000002];
        for (int[] interval: intervals) {
            changes[interval[0]]++;
            changes[interval[1] + 1]--;
        }
        int maxIntersections = 0;
        int currentIntersections = 0;
        for (int change: changes) {
            if (change == 0) continue;
            currentIntersections += change;
            if (change > 0 && currentIntersections > maxIntersections)
                maxIntersections = currentIntersections;
        }
        return maxIntersections;
    }

    /*
    V předchozí verzi procházím spoustu nul (zbytečně). Alternativa: udělám si mapu, kde můžu mít jen klíče,
    na které šahám (přičítám/odečítám 1), pak procházím jenom ty.
    Cca 5x pomalejší než předchozí řešení.
     */
    public static int minGroupsOtherSolutionUpgraded(int[][] intervals) {
        SortedMap<Integer, Integer> changes = new TreeMap<Integer, Integer>();
        for (int[] interval: intervals) {
            changes.put(interval[0], changes.getOrDefault(interval[0], 0) + 1);
            changes.put(interval[1] + 1, changes.getOrDefault(interval[1] + 1, 0) - 1);
        }
        int maxIntersections = 0;
        int currentIntersections = 0;
        for (int change: changes.values()) {
            currentIntersections += change;
            if (change > 0 && currentIntersections > maxIntersections)
                maxIntersections = currentIntersections;
        }
        return maxIntersections;
    }

    public static void main(String[] args) {

    }
}
