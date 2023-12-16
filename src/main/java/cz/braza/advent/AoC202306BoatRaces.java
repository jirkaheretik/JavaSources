package cz.braza.advent;

public class AoC202306BoatRaces {
    public static void main(String[] args) {
        // times and records, last one is for PART 2 and handled differently:
        int[] times = {46, 85, 75, 82, 46857582};
        long[] records = {208, 1412, 1257, 1410, 208141212571410L};
        long totalWays = 1; // we will be multiplying
        long totalDist = 0; // Bonus: total travel in all the races
        long totalOverRecords = 0;
        for (int t = 0; t < times.length; t++) {
            long timeAllowed = times[t];
            long ways = 0; // to beat a record
            long best = 0; // BONUS: best way
            for (long i = 1; i < timeAllowed; i++) { // I hold for "i" millisecods to reach the speed of "i" for the rest of the time. Enough?
                long distTravelled = (timeAllowed - i) * i;
                if (distTravelled > records[t])
                    ways++;
                if (distTravelled > best)
                    best = distTravelled;
            }
            if (t < times.length - 1) {
                totalWays *= ways;
                // bonus:
                totalDist += best;
                totalOverRecords += best - records[t];
            } else {
                System.out.println("Part 2: Total number of ways: " + ways);
                System.out.println("Best distance for part 2 is " + best + " which is " + (best - records[t]) + " over current record.");
            }
        }
        System.out.println("Part 1: Number of ways: " + totalWays);
        System.out.println("Total maximum distance travelled in part 1: " + totalDist + ", beating records by " + totalOverRecords + " in total.");
    }
}