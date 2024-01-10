package cz.braza.advent.a2015;

public class Day17Eggnog {
    public static final int[] VOLUMES = {11, 30, 47, 31, 32, 36, 3, 1, 5, 3, 32, 36, 15, 11, 46, 26, 28, 1, 19, 3};
    public static void main(String[] args) {
        // init:
        final int[] POWERS = new int[VOLUMES.length];
        POWERS[0] = 1;
        for (int i = 1; i < VOLUMES.length; i++)
            POWERS[i] = 2 * POWERS[i - 1];
        // go for it
        int ways = 0;
        int minContainers = 20;
        int numWays = 1;
        for (int flags = 0; flags < 1024 * 1024; flags++) {
            int eggnog = 0;
            int containers = 0;
            for (int pow = 0; pow < POWERS.length; pow++)
                if ((flags & POWERS[pow]) > 0) {
                    eggnog += VOLUMES[pow];
                    containers++;
                }
            if (eggnog == 150) {
                ways++;
                if (containers < minContainers) {
                    numWays = 1;
                    minContainers = containers;
                } else if (containers == minContainers)
                    numWays++;
            }
        }
        System.out.println("Part 1: number of ways: " + ways);
        System.out.println("Part 2: There are " + numWays + " ways to get 150 litres of eggnog into " + minContainers + " containers.");
    }
}
