package cz.braza.advent.a2024;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day14RobotsOutsideBathroom {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc24_14.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        /*
        int sizeX = 11;
        int sizeY = 7;
        int robotsCount = 12;
        */
        int sizeX = 101;
        int sizeY = 103;
        int robotsCount = 500;
        int[][] robots = new int[robotsCount][4];
        int robotIdx = 0;
        while (vstup.hasNextLine()) {
            String[] line = vstup.nextLine().split(" ");
            String[] pos = line[0].substring(2).split(",");
            String[] vel = line[1].substring(2).split(",");
            int px = Integer.parseInt(pos[0]);
            int py = Integer.parseInt(pos[1]);
            int vx = Integer.parseInt(vel[0]);
            int vy = Integer.parseInt(vel[1]);
            int endX = (px + 100 * vx) % sizeX;
            int endY = (py + 100 * vy) % sizeY;
            if (endX < 0) endX += sizeX;
            if (endY < 0) endY += sizeY;
            System.out.printf("Robot at [%d,%d] moving direction [%d,%d] ending at [%d,%d]%n", px, py, vx, vy, endX, endY);
            if (endX < sizeX / 2 && endY < sizeY / 2) q1++;
            if (endX < sizeX / 2 && endY > sizeY / 2) q2++;
            if (endX > sizeX / 2 && endY < sizeY / 2) q3++;
            if (endX > sizeX / 2 && endY > sizeY / 2) q4++;
            robots[robotIdx++] = new int[]{px, py, vx, vy};
        }
        System.out.printf("Part 1 quadrants have count %d, %d, %d and %d, total safety factor is %d.%n", q1, q2, q3, q4, q1 * q2 * q3 * q4);
        /*
        // CAUTION! This creates 11 thousands images!
        // After emulating that many robots steps.
        // The correct one (for a star) is 7132.
        int step = 0;
        int dot = new Color(180, 180, 180).getRGB();
        do {
            BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
            for (int r = 0; r < robots.length; r++) {
                img.setRGB(robots[r][0], robots[r][1], dot);
                robots[r][0] = (robots[r][0] + robots[r][2]) % sizeX;
                robots[r][1] = (robots[r][1] + robots[r][3]) % sizeY;
                if (robots[r][0] < 0) robots[r][0] += sizeX;
                if (robots[r][1] < 0) robots[r][1] += sizeY;
            }
            try {
                File output = new File("/home/jirka/src/java0/day14_" + step + ".png");
                ImageIO.write(img, "png", output);
            } catch (IOException ioe) {}
        } while (step++ < 11000);
         */
    }
}
