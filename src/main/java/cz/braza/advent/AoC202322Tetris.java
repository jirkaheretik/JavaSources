package cz.braza.advent;

import java.io.File;
import java.util.*;

public class AoC202322Tetris {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_22.txt";
        Scanner vstup = new Scanner(new File(fileName));
        ArrayList<Integer> left = new ArrayList<>();
        Map<Integer, String> right = new HashMap<>();
        while (vstup.hasNextLine()) {
            String[] coords = vstup.nextLine().split("~");
            String[] lft = coords[0].split(",");
            String[] rgt = coords[1].split(",");
            int x1 = Integer.parseInt(lft[0]);
            int y1 = Integer.parseInt(lft[1]);
            int z1 = Integer.parseInt(lft[2]);
            int x2 = Integer.parseInt(rgt[0]);
            int y2 = Integer.parseInt(rgt[1]);
            int z2 = Integer.parseInt(rgt[2]);
            int val = z1 * 100 + 10 * y1 + x1;
            left.add(val);
            int dx = (x2 - x1 + 1);
            int dy = (y2 - y1 + 1);
            int dz = (z2 - z1 + 1);
            String mapVal = dz > 1 ? "z" + dz : dy > 1 ? "y" + dy : "x" + dx;
            //if (dx > 9 || dy > 9 || dz > 9 || ((dx * dy * dz) > (dx + dy + dz)))
            //    System.out.println(dx + "x" + dy + "x" + dz + " " + mapVal);
            right.put(val, mapVal);
        }
        Collections.sort(left);
        for (int block : left) {
            String mapVal = right.get(block);
            int x = block % 10;
            int y = (block / 10) % 10;
            int z = block / 100;
            System.out.println("x=" + x + ", y=" + y + ", z=" + z + " " + mapVal);
        }
        // sorting ok, parsing ok
        // Processed 1439 records, x ranging from 0 to 9, while y ranging from 0 to 9

        // now:
        // 1. go through the list, for each block find if there is enough space below, and if so, let it fall through
        //    Remember former key in order to update both left and right collections
        // 2. sort again left :-)
        Collections.sort(left);
        // 3. then go upwards, and for each block find the one above (if any) and from it all that are below it
        // 4. count those that don't have anything above (and print, if any at all) and those that are supported by multiple bricks


        // ERR, tried 15884 from another puzzle, answer too high
    }
}
