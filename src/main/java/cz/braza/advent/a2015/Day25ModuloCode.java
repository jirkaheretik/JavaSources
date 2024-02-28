package cz.braza.advent.a2015;

public class Day25ModuloCode {
    public static final long START = 20151125;
    public static final long MULTI = 252533;
    public static final long MODULO = 33554393;
    public static void main(String[] args) {
        // 0. input: row 3010, column 3019
        // 1. find out number of repetitions *rep*
        int R = 3010;
        int C = 3019;
        long rep = order(R, C);
        long rep1 = order3(R, C);
        long rep2 = cycleOrder(R, C);
        long rep3 = lukasOrder(R, C);
        long rep4 = lukasOrder2(R, C);
        System.out.println("Fast: " + rep + " or " + rep1 + " with diff " + (rep1 - rep) + ", slow: " + rep2 + ", Lukáš: " + rep3 + ", já/Lukáš: " + rep4 + ", diff: " + (rep4 - rep3));
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

    public static long order3(int row, int col) {
        return (row + col) * (row + col - 1) / 2 - row + 1;
    }

    public static long lukasOrder(int row, int col) {
        int codeNumber = 1;
        int currentStep = 1;

        for (int i = 1; i < col; i++) {
            currentStep = i + 1;
            codeNumber += currentStep;
        }

        for (int j = 1; j < row; j++) {
            codeNumber += currentStep++;
        }
        return codeNumber;
    }
    public static long lukasOrder2(int row, int col) {
        int codeNumber = 1;
        for (int i = 1; i < col + row - 1; i++) {
            codeNumber += i + 1;
        }
        return codeNumber - row + 1;
    }

    public static long cycleOrder(int row, int col) {
        int count = 1;
        int x = 1;
        int y = 1;
        while (x != col || y != row) {
            count++;
            x++;
            y--;
            if (y < 1) {
                y = x;
                x = 1;
            }
        }
        return count;
    }

    // if we want to make it short
    public static void doPart1() {
        long rep = (3010 + 3019) * (3010 + 3019 - 1) / 2 - 3009;
        long current = 20151125;
        for (long i = 1; i < rep; i++)
            current = (current * 252533) % 33554393;
        System.out.println(current);
    }
}
