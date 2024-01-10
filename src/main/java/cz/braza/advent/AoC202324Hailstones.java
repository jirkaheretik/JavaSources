package cz.braza.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Hailstone {
    long x;
    long y;
    long z;
    int vx;
    int vy;
    int vz;
    long c;
    public Hailstone(long ix, long iy, long iz, int ivx, int ivy, int ivz) {
        x = ix;
        y = iy;
        z = iz;
        vx = ivx;
        vy = ivy;
        vz = ivz;
        c = vx * y - vy * x;
    }

    @Override
    public String toString() {
        return x + ", " + y+ ", " + z + " @ " + vx + ", " + vy + ", " + vz + " (" + c + ")";
    }
}

public class AoC202324Hailstones {
    //public static final long MIN = 7;
    public static final long MIN = 200000000000000L;
    //public static final long MAX = 27;
    public static final long MAX = 400000000000000L;

    public static int errCount = 0;

    public static int paralelCount = 0;

    public static int pastCount = 0;

    public static int missedAreaCount = 0;


    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_24.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int crashCount = 0;
        List<Hailstone> hails = new ArrayList<>();
        while (vstup.hasNextLine()) {
            String[] line = vstup.nextLine().split(" @ ");
            String[] pos = line[0].split(", ");
            String[] vel = line[1].split(", ");
            Hailstone newH = new Hailstone(
                    Long.parseLong(pos[0]),
                    Long.parseLong(pos[1]),
                    Long.parseLong(pos[2]),
                    Integer.parseInt(vel[0]),
                    Integer.parseInt(vel[1]),
                    Integer.parseInt(vel[2])
            );
            for (Hailstone h: hails) {
                if (crashCourseInFuture(h, newH))
                    crashCount++;
            }
            hails.add(newH);
        }
        System.out.println("Part 1: number of pairs colliding in future: " + crashCount + " and " + errCount + " potential errors and " + paralelCount + " paralel directions. Past cross count: " + pastCount + " and missed count: " + missedAreaCount);
        //Part 1: number of pairs colliding in future: 8163 and 1 potential errors and 19 paralel directions.
        // 8163, 8164, 8183 -- too low :-(
        // after a fix:
        // Part 1: number of pairs colliding in future: 15883 and 0 potential errors and 19 paralel directions. Past cross count: 19629 and missed count: 9319
        // 15883, 15884, 15885, 15886, 15887, 15888,  not the right answer
        // 15889 - thats the right answer!
    }

    public static boolean crashCourseInFuture(Hailstone h1, Hailstone h2) {
        // 1. get the vectors
        // ax + by + c = 0
        // a == -vy, b == vx  => c:
        double d1 = ((double) h1.vx) / h2.vx;
        double d2 = ((double) h1.vy) / h2.vy;
        // 2. find if they intersect
        if (Math.abs(d1) == Math.abs(d2)) {
            //System.out.println("Same vectors found");
            double tx1 = (h2.x - h1.x) / h1.vx;
            double ty1 = (h2.y - h1.y) / h1.vy;
            double tx2 = (h1.x - h2.x) / h2.vx;
            double ty2 = (h1.y - h2.y) / h2.vy;
            if (tx1 != ty1 || tx2 != ty2)
                System.out.println("Parallel lines " + h1 + " and " + h2 + ", they don't intersect.");
            else if (tx1 < 0 || tx2 < 0)
                System.out.println("Same line, different directions for " + h1 + " and " + h2);
            else {
                System.out.println("Parallel lines aiming towards the other, counting.");
                return true;
            }
            paralelCount++;
        } else {
            // System.out.println(-h1.vy + "x + " + h1.vx + "y + " + c1 + "=0  vs  " + (-h2.vy) + "x + " + h2.vx + "y + " + c2 + "=0");
            // double t = (h2.vy*h1.y - h2.vx*h2.y - h2.vy*h1.x + h2.x*h2.vy) / (h2.vy*h1.vx - h2.vx*h1.vy);
            double t = 0;
            double s = 0;
            try {
                // Somehow this does not always produce the right result:
                //t = ((double) (h1.y * h2.vx + h2.x * h2.vy - h2.y * h2.vx - h1.x * h2.vy)) / ((double) (h1.vx * h2.vx - h1.vy * h2.vy));
                s = ((double) (h2.y * h1.vx + h1.x * h1.vy - h1.y * h1.vx - h2.x * h1.vy)) / ((double) (h2.vx * h1.vy - h2.vy * h1.vx));
                t = (h2.vx * s + h2.x - h1.x) / (double) h1.vx;
            }
            catch (ArithmeticException ae) {
                System.out.println("Aritmetic Exception ");
                errCount++;
                return false;
            }
            double xx1 = h1.x + h1.vx * t;
            double xy1 = h1.y + h1.vy * t;
            double xx2 = h2.x + h2.vx * s;
            double xy2 = h2.y + h2.vy * s;
            //System.out.println("Intersect at [" + xx1 + ", " + xy1 + "] or [" + xx2 + ", " + xy2 + "]");
            // 3. check it is in future
            if (t < 0 || s < 0) {
                pastCount++;

                //System.out.println(h1 + " and " + h2 + " cross in the past, not counting (t = " + t + ", s =" + s + ")");
            } else {
                // 4. check if it is in target area
                double x0 = h1.x + h1.vx * t;
                double y0 = h1.y + h1.vy * t;
                if (x0 >= MIN && x0 <= MAX && y0 >= MIN && y0 <= MAX) {
                    //System.out.println("Found interset of " + h1 + " and " + h2 + ", t = " + t + ", s = " + s);
                    return true;
                } else {
                    missedAreaCount++;
                    //System.out.println("Missed target area: " + h1 + " and " + h2 + " crossing at " + x0 + ", " + y0);
                }
            }
        }
        return false;
    }
}
