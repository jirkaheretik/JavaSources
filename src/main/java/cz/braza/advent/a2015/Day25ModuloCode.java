package cz.braza.advent.a2015;

public class Day25ModuloCode {
    public static final long START = 20151125;
    public static final long MULTI = 252533;
    public static final long MODULO = 33554393;
    public static void main(String[] args) {
        // 0. input: row 3010, column 3019
        // 1. find out number of repetitions *rep*
        long rep = order(3010, 3019);
        // 2. repeat the process
        long current = START;
        for (long i = 1; i < rep; i++)
            current = (current * MULTI) % MODULO;
        System.out.println(rep + ". " + current);
        doPart1();
    }

    public static long order(int row, int col) {
        long rowStart = row + col - 1;
        return rowStart * (rowStart - 1) / 2 + col;
    }

    // if we want to make it short
    public static void doPart1() {
        long rep = (3010 + 3019 - 1) * (3010 + 3019 - 2) / 2 + 3019;
        long current = 20151125;
        for (long i = 1; i < rep; i++)
            current = (current * 252533) % 33554393;
        System.out.println(current);
    }
}
