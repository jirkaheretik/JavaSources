package cz.braza.educanet;

import java.util.ArrayList;
import java.util.List;


class Kombinace {
    private static final int soucet = 3333;
    private List<Platidlo> platidla;  //list vsech platidel


    public Kombinace() {




        platidla = new ArrayList<>();


        platidla.add(new Platidlo(1, 10));
        platidla.add(new Platidlo(2, 10));
        platidla.add(new Platidlo(5, 10));
        platidla.add(new Platidlo(10, 10));
        platidla.add(new Platidlo(20, 10));
        platidla.add(new Platidlo(50, 10));
        platidla.add(new Platidlo(100, 10));
        platidla.add(new Platidlo(200, 10));
        platidla.add(new Platidlo(280, 10));
        /*
        platidla.add(new Platidlo(500, 10));
        platidla.add(new Platidlo(1000, 10));
        platidla.add(new Platidlo(2000, 10));

         */
    }


    public void pocitej() {
        int kombinace = kombVypocet(soucet, 0, new int[platidla.size()]);
        System.out.println("počet kombinací: " + kombinace);
    }


    private int kombVypocet(int zbytek, int indexPlatidla, int[] counts) {
        if (zbytek == 0) {
            return 1;
        }
        if (zbytek < 0 || indexPlatidla >= platidla.size()) {
            return 0;
        }


        int kombCount = 0;
        Platidlo currentPlatidlo = platidla.get(indexPlatidla); //vezme se platidlo


        for (int count = 0; count <= currentPlatidlo.getMaxCount(); count++) {
            if (count * currentPlatidlo.getValue() <= zbytek) {
                counts[indexPlatidla] = count;
                kombCount += kombVypocet(zbytek - count * currentPlatidlo.getValue(),indexPlatidla + 1, counts);
            } else {
                break;
            }
        }


        return kombCount;
    }


    static class Platidlo {
        private final int value;
        private final int maxCount;


        public Platidlo(int value, int maxCount) {
            this.value = value;
            this.maxCount = maxCount;
        }


        public int getValue() {
            return value;
        }


        public int getMaxCount() {
            return maxCount;
        }
    }
}
public class Test {
    public static void main(String[] args) {
        System.out.println("cekej");
        Kombinace vypocet = new Kombinace();
        vypocet.pocitej();




    }
}

