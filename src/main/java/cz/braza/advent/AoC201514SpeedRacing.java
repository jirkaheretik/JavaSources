package cz.braza.advent;

class Racer {
    private int kmps;
    private int raceTime;
    private int restTime;
    public Racer(int k, int race, int rest) {
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
}
public class AoC201514SpeedRacing {
    public static void main(String[] args) {
        Racer rudolph = new Racer(22, 8, 165);
        Racer cupid = new Racer(8, 17, 114);
        Racer prancer = new Racer(18, 6, 103);
        Racer donner = new Racer(25, 6, 145);
        Racer dasher = new Racer(11, 12, 125);
        Racer comet = new Racer(21, 6, 121);
        Racer blitzen = new Racer(18, 3, 50);
        Racer vixen = new Racer(20, 4, 75);
        Racer dancer = new Racer(7, 20, 119);
        Racer[] racers = { rudolph, cupid, prancer, donner, dasher, comet, blitzen, vixen, dancer};
        int max = 0;
        for (Racer r : racers) {
            int farth = r.kmsInTime(2503);
            System.out.println(farth);
            if (max < farth) max = farth;
        }
        System.out.println("Fastest racer at 2503 sec has travelled " + max  + " km.");
    }
}