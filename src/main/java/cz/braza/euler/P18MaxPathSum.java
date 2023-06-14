package cz.braza.euler;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Triangle, Nth row contains N numbers
 * Go from top to bottom, always 2 paths to choose from, and find a path with a highest sum.
 * Example:
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 * Highest is 3 + 7 + 4 + 9 = 23
 *
 * Now, P18: 15 rows and columns
 * P67: 100 rows and columns
 *
 * Solution:
 * For each row, you can get to upper row to the same index or index-1 (if they exist), always picking higher number.
 * Thus, we can always remember just two rows, and a maximum in currently processed row.
 */
public class P18MaxPathSum {
    public static void main(String[] args) {
        BufferedReader br;
        try {
            // Filenames: P18test.txt, P18.txt, P67.txt
            InputStreamReader streamReader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("P67.txt"), StandardCharsets.UTF_8);
            br = new BufferedReader(streamReader);
        String strLine;
        int[] predchoziRadek = {0};
        int[] radek;
        int maximum = 0;
        // nacti radek:
        // zpracuj radek: int[] radek
        while ((strLine = br.readLine()) != null) {
            String[] cisla = strLine.split(" ");
            radek = new int[cisla.length];
            for (int i = 0; i < cisla.length; i++)
                radek[i] = Integer.parseInt(cisla[i]);

            maximum = 0;
            for (int i = 0; i < radek.length; i++) {
                int predchozi = Math.max(i > 0 ? predchoziRadek[i - 1] : 0, (i < radek.length - 1) ? predchoziRadek[i] : 0);
                radek[i] += predchozi;
                if (radek[i] > maximum)
                    maximum = radek[i];
            }
            // je-li dalsi radek:
            predchoziRadek = radek;
            // jinak (neni-li dalsi radek):
        }
        System.out.println(maximum);
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
