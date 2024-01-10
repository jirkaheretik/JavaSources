package cz.braza.advent.a2015;

class Racer {
    private int kmps;
    private int raceTime;
    private int restTime;
    private String name;
    private int points = 0;
    private int distance = 0;
    public Racer(String n, int k, int race, int rest) {
        name = n;
        kmps = k;
        raceTime = race;
        restTime = rest;
    }
    public int kmsInTime(int sec) {
        int result = 0;
        while (sec > 0) {
            if (sec >= raceTime) {
                sec -= raceTime;
                result += raceTime * kmps;
            } else {
                result += sec * kmps;
                sec = 0;
            }
            sec -= restTime;
        }
        return result;
    }

    public void addPoint() { points++; }
    public int getPoints() { return points; }
    @Override
    public String toString() {
        return "Reindeer " + name + " with " + points + " points, that is at " + kmsInTime(2503);
    }
}
public class Day14SpeedRacing {
    public static void main(String[] args) {
        Racer[] racers = { new Racer("Rudolph", 22, 8, 165),
                new Racer("Cupid", 8, 17, 114),
                new Racer("Prancer", 18, 6, 103),
                new Racer("Donner", 25, 6, 145),
                new Racer("Dasher", 11, 12, 125),
                new Racer("Comet", 21, 6, 121),
                new Racer("Blitzen", 18, 3, 50),
                new Racer("Vixen", 20, 4, 75),
                new Racer("Dancer", 7, 20, 119) };
        int max = 0;
        for (Racer r : racers) {
            int farth = r.kmsInTime(2503);
            // System.out.println(farth);
            if (max < farth) max = farth;
        }
        System.out.println("Fastest racer at 2503 sec has travelled " + max  + " km.");
        for (int time = 1; time <= 2503; time++) {
            int max2 = 0;
            for (Racer r : racers) {
                int go = r.kmsInTime(time);
                if (go > max2)
                    max2 = go;
            }
            for (Racer r: racers)
                if (max2 == r.kmsInTime(time))
                    r.addPoint();
        }
        int maxPoints = 0;
        for (Racer r: racers) {
            System.out.println(r);
            if (r.getPoints() > maxPoints) maxPoints = r.getPoints();
        }
        System.out.println("Part 2, winning reindeer has " + maxPoints + " points.");
    }
}