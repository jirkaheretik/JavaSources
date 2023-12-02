package cz.braza.educanet;

import java.io.File;
import java.util.Scanner;

/**
 * Given the same CSV input (GitHub Issues), find out which year-quarter had HIGHEST and LOWEST *TOTAL*
 * number of issues.
 * Feel free to use the information that data are sorted by year and quarter in order NOT TO store them in memory.
 */
public class GithubIssuesQuartals {
    public static void main(String[] args) throws Exception {
        String filename = "/home/jirka/src/java0/issues.csv";
        int minIssues = Integer.MAX_VALUE;
        int maxIssues = 0;
        String minQuarter = "";
        String maxQuarter = "";
        int currentYear = 0;
        int currentQuarter = 0;
        int currentIssuesSum = 0;
        Scanner sc = new Scanner(new File(filename));
        sc.nextLine();  // get rid of table header

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            int year = Integer.parseInt(data[1]);
            int quarter = Integer.parseInt(data[2]);
            int issues = Integer.parseInt(data[3]);
            if (year != currentYear || quarter != currentQuarter) {
                // conclude previous quarter
                if (currentQuarter != 0) {
                    if (currentIssuesSum > maxIssues) {
                        maxIssues = currentIssuesSum;
                        maxQuarter = currentYear + "/" + currentQuarter;
                    }
                    if (currentIssuesSum < minIssues) {
                        minIssues = currentIssuesSum;
                        minQuarter = currentYear + "/" + currentQuarter;
                    }
                }
                currentIssuesSum = 0;
                currentYear = year;
                currentQuarter = quarter;
            }
            currentIssuesSum += issues;
        }
        System.out.println("Max issues: " + maxIssues + " in " + maxQuarter);
        System.out.println("Min issues: " + minIssues + " in " + minQuarter);


    }
}
