package cz.braza.euler;

public class P94AlmostEquilateral {
    public static void main(String[] args) {
        System.out.println(java.time.Instant.now());
        long sumOfPerimeters = 0;
        for (int r = 3; r < 333_333_334; r += 2) {
            if (isIntegral(r, r - 1)) {
                sumOfPerimeters += 3 * r - 1;
                System.out.println(r + ", " + r + ", " + (r - 1));
            }
            if (isIntegral(r, r + 1)) {
                sumOfPerimeters += 3 * r + 1;
                System.out.println(r + ", " + r + ", " + (r + 1));
            }
        }
        System.out.println("Sum of perimeters: " + sumOfPerimeters);
        System.out.println(java.time.Instant.now());
    }

    public static boolean isIntegral(long side, long base) {
        long half = base / 2;
        double height = Math.sqrt(side * side - half * half);
        long hght = (int) height;
        if (height != hght) return false;
        return half * half + hght * hght == side * side; // check if it adds up back
        /*
        if (half * half + hght * hght == side * side) return true;
        else {
            System.out.println("Possible loss of precision");
            return false;
        }
         */
    }
}
