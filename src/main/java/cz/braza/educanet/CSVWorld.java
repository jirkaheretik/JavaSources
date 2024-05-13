package cz.braza.educanet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Country {
    String name;
    String continent;
    int area;
    int population;
    long gdp;

    public Country(String n, String c, int a, int p, long g) {
        name = n;
        continent = c;
        area = a;
        population = p;
        gdp = g;
    }

    @Override
    public String toString() {
        return name + " (" + continent + "), " + area + " km2, " + population + " ppl and GDP: " + gdp;
    }
}

public class CSVWorld {
    public static final String filename = "/home/jirka/src/java0/world.csv";
    public static void main(String[] args) {
        List<Country> countries = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filename));
            String[] popisky = sc.nextLine().split(",");
            while (sc.hasNextLine()) {
                String radek = sc.nextLine();
                String[] data = radek.split(",");
                countries.add(new Country(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Long.parseLong(data[4])));
            }
            System.out.println("Data loaded. Countries: " + countries.size());
            System.out.println("Printing big countries:");
            printBigCountries(countries, 230000000);
            System.out.println();
            System.out.println("\nEurope area:");
            System.out.println(getSumOfContinentArea(countries, "Europe"));
            System.out.println("\nEurope population:");
            System.out.println(getSumOfContinentPopulation(countries, "Europe"));
            System.out.println(getAvgGdpForRichCountries(countries, 1_000_000_000_000L));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void printBigCountries(List<Country> countries, int minPopulation) {
        countries.stream().filter(x -> x.population >= minPopulation).forEach(System.out::println);
    }

    public static void printLargeCountries(List<Country> countries, int minArea) {
        countries.stream().filter(x -> x.area >= minArea).forEach(System.out::println);
    }

    public static int getSumOfContinentPopulation(List<Country> countries, String continent) {
        return countries.stream().filter(x -> x.continent.equals(continent)).mapToInt(x -> x.population).sum();
    }

    public static int getSumOfContinentArea(List<Country> countries, String continent) {
        return countries.stream().filter(x -> x.continent.equals(continent)).mapToInt(x -> x.area).sum();
    }

    public static long getAvgGdpForRichCountries(List<Country> countries, long minGdp) {
        List<Country> filtered = countries.stream().filter(x -> x.gdp >= minGdp).toList();
        return filtered.stream().mapToLong(x -> x.gdp).sum() / filtered.stream().mapToLong(x -> x.population).sum();
    }
}
