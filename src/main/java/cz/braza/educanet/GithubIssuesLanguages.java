package cz.braza.educanet;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Same file with GitHub issues as @see GithubIssuesQuarters and others.
 * Given the input of (two) languages, create a new CSV file with quarters and appropriate issue count for given languages.
 */
public class GithubIssuesLanguages {
    public static void main(String[] args) throws Exception {
        // 1. read languages from the command line and prepare working arrays
        System.out.println("Enter comma separated list of languages to process:");
        Scanner sc = new Scanner(System.in);
        String[] langs = sc.nextLine().split(",");
        if (langs.length < 2) {
            System.out.println("There is nothing to do if you do not specify at least two languages. Exitting.");
            return;
        }
        String[] languages = new String[langs.length];
        String[] issueCounts = new String[langs.length];
        Arrays.fill(issueCounts, "0");
        for (int i = 0; i < langs.length; i++) {
            languages[i] = langs[i].trim();
        }

        // 2. prepare output file:
        String pathname = "/home/jirka/src/java0/";
        String inName = "issues.csv";
        String outName = "issues-out.csv";
        FileWriter writer = new FileWriter(pathname + outName);
        writer.write("Year,Quarter," + String.join(",", languages) + "\n");

        // 3. read in and process CSV file
        sc = new Scanner(new File(pathname + inName));
        sc.nextLine();  // get rid of table header
        int currentYear = 0;
        int currentQuarter = 0;

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            int year = Integer.parseInt(data[1]);
            int quarter = Integer.parseInt(data[2]);
            String issues = data[3]; // no parsing (and comparing) needed, easier to work with strings
            // first, check if the year or quarter has changed, and process it
            if (year != currentYear || quarter != currentQuarter) {
                // conclude previous quarter
                if (currentQuarter != 0) {
                    writer.write(currentYear + "," + currentQuarter + "," + String.join(",", issueCounts) + "\n");
                    Arrays.fill(issueCounts, "0"); // empty all the values
                }
                currentYear = year;
                currentQuarter = quarter;
            }
            // now find if we need the value and save it accordingly:
            for (int idx = 0; idx < languages.length; idx++) {
                if (languages[idx].equals(data[0]))
                    issueCounts[idx] = issues;
            }
        }
        // do not forget to write the last quarter:
        writer.write(currentYear + "," + currentQuarter + "," + String.join(",", issueCounts) + "\n");
        writer.close();
        sc.close();
        // All Done:
        System.out.println("All done, results saved into file " + outName + ", do NOT forget to rename it before the next run if you want to keep the data.");
    }
}
