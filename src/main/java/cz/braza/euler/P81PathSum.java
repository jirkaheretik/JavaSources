package cz.braza.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class P81PathSum {
    public static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) {
        BufferedReader br;
        try {
            // Filenames: P81matrix.txt, P81test5x5.txt
            String filename = "P81matrix.txt";
            InputStreamReader streamReader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8);
            br = new BufferedReader(streamReader);
            String strLine;
            boolean hasPrevLine = false;
            int[] predchoziRadek = {};
            int[] radek = null;
            // nacti radek:
            // zpracuj radek: int[] radek
            while ((strLine = br.readLine()) != null) {
                String[] cisla = strLine.split(",");
                radek = new int[cisla.length];
                for (int i = 0; i < cisla.length; i++)
                    radek[i] = Integer.parseInt(cisla[i]);

                for (int i = 0; i < radek.length; i++) {
                    int predchozi = Math.min(hasPrevLine ? predchoziRadek[i] : MAX, (i > 0) ? radek[i - 1] : MAX);
                    if (predchozi == MAX)
                        predchozi = 0;
                    radek[i] += predchozi;
                }
                // je-li dalsi radek:
                predchoziRadek = radek;
                hasPrevLine = true;
                // jinak (neni-li dalsi radek):
            }
            System.out.println(radek[radek.length - 1]);
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
