package cz.braza.educanet;

import java.io.File;
import java.util.Scanner;

public class GithubIssues {
    public static void main(String[] args) throws Exception {
        String filename = "/home/jirka/src/java0/issues.csv";
        int maxIssues = 0;
        int secondIssues = 0;
        int totalIssues = 0;
        String languages = ",";
        int languageCount = 0;
        String maxLanguage = "";
        String secondLanguage = "";
        String year = "2019";
        String quarter = "2";
        String language = "JavaScript";
        String lang1 = "PHP";
        int issues1 = 0;
        String lang2 = "C++";
        int issues2 = 0;
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(",");
            if (data[1].equals(year)) {
                int iss = Integer.parseInt(data[3]);
                if (!languages.contains("," + data[0] + ",")) {
                    languageCount++;
                    languages = languages + data[0] + ",";
                }
                if (data[0].equals(lang1))
                    issues1 += iss;
                if (data[0].equals(lang2))
                    issues2 += iss;
                if (data[2].equals(quarter)) {
                    totalIssues += iss;
                    if (iss > maxIssues) {
                        secondIssues = maxIssues;
                        secondLanguage = maxLanguage;
                        maxIssues = iss;
                        maxLanguage = data[0];
                    } else if (iss > secondIssues) {
                        secondIssues = iss;
                        secondLanguage = data[0];
                    }
                }
            }

                // System.out.println(data[0] + " in " + data[2] + " quarter of " + data[1] + " had " + data[3] + " issues.");
        }
        System.out.println("Max issues (" + maxIssues + ") in " + year + "/" + quarter + " had " + maxLanguage);
        System.out.println("Second max issues (" + secondIssues + ") had " + secondLanguage);
        System.out.println("There were total of " + totalIssues + " and " + maxLanguage + " accounted for " + Math.round(100.0 * maxIssues / totalIssues ) + " % of them.");
        System.out.println(issues1 + " total issues for " + lang1 + ", " + issues2 + " total issues for " + lang2);
        System.out.println(languageCount);
        System.out.println(languages);
        System.out.println(languages.split(",").length - 1);
    }
}
