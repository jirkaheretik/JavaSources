package cz.braza;

import cz.braza.zaklady.Zaklady14;

import java.util.Random;

public class Vlakna extends Thread {
    Random generator = new Random();
    String jmeno = "unknown";
    public Vlakna(String name) {
        jmeno = name;
    }

    public void run() {
        int pocet = generator.nextInt(2, 12);
        int runCount = 0;
        while (runCount < pocet) {
            runCount++;
            System.out.println(jmeno + " " + runCount);
            try {
                Thread.sleep(generator.nextInt(1000));
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }
        System.out.println(jmeno + " END.");

    }

    public static void main(String[] args) {
        String[] jmena = {"Karel", "Thomas", "Jim", "Johny", "Mark", "Earl", "Billy", "Arnulfo"};
        for (String s: jmena)
            (new Vlakna(s)).start();

        XYZ pokus = new XYZ();
        Thread foo = new Thread(pokus);
        foo.start();

        (new Thread(new XYZ())).start();
        System.out.println("Main program ended.");
    }

}

class DalsiVlakno extends Thread {
    public void run() {
        System.out.println("Dalsi vlakno");
    }
}

class XYZ extends Zaklady14 implements Runnable {
    public void run() {
        System.out.println("XYZ extends Zaklady");
    }
}

