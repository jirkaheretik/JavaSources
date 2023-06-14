package cz.braza.euler;

/**
 * How many possible routes there are in a 20x20 grid from top-left to bottom-right corner if we can only go right or down?
 */
public class P15LatticePaths {

    public static void main(String[] args) {
        final int maxX = 21;
        final int maxY = 21;
        long[][] grid = new long[maxY][maxX];
        for (int x = 0; x < maxX; x++)
            grid[0][x] = 1;
        for (int y = 0; y < maxY; y++)
            grid[y][0] = 1;
        for (int y = 1; y < maxY; y++)
            for (int x = 1; x < maxX; x++)
                grid[y][x] = grid[y-1][x] + grid[y][x-1];
        for (int i = 1; i < maxX; i++)
            System.out.println(grid[i][i]);
    }
}
