package cz.braza.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AFile {
    String name;
    int size;
    public AFile(String n, int s) {
        name = n;
        size = s;
    }
    public int getSize() { return size; }
}

class ADir extends AFile {
    List<AFile> content = new ArrayList<>();
    ADir parent = null;
    public ADir(String n, ADir p) {
        super(n, 0);
        parent = p;
    }
    public void addItem(String name, int size) {
        if (size > 0)
            content.add(new AFile(name, size));
        else
            content.add(new ADir(name, this));
    }
    public int getSize() {
        int total = 0;
        for (AFile af: content)
            total += af.getSize();
        return total;
    }
    public int findSumOfSizes(int limit) {
        int mySize = getSize();
        int mySum = 0;
        for (AFile af: content)
            if (af instanceof ADir ad)
                mySum += ad.findSumOfSizes(limit);
        return mySum + ((mySize <= limit) ? mySize : 0);
    }
    public ADir getDir(String name) {
        for (AFile af: content) {
            if (af instanceof ADir ad && af.name.equals(name))
                return ad;
            else if (af.name.equals(name)) {
                System.out.println("Seems there is also a file '" + name + "' with the same name in dir " + this.name);
            }
        }
        System.out.println("Not found a directory " + name + " in a directory " + this.name);
        return null; //
    }
    public ADir getParent() {
        if (parent == null) {
            System.out.println("Trying to escape from directory " + name + " that does not have a parent set, setting it to itself.");
            parent = this;
        }
        return parent;
    }
    public List<ADir> getMyDirs() {
        List<ADir> result = new ArrayList<>();
        result.add(this);
        for (AFile af: content)
            if (af instanceof ADir ad)
                result.addAll(ad.getMyDirs());
        return result;
    }
    public int smallestLargerThan(int limit) {
        int minFound = Integer.MAX_VALUE;
        List<ADir> dirs = getMyDirs();
        for (ADir ad: dirs) {
            int size = ad.getSize();
            if (size >= limit && size < minFound) {
                System.out.println("Directory " + ad.name + " contains " + size + " bytes, possible candidate.");
                minFound = size;
            }
        }
        return minFound;
    }
}

public class AoC202207Directories {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc22_07.txt";
        Scanner vstup = new Scanner(new File(fileName));
        // ignore first "cd /"
        vstup.nextLine();
        ADir root = new ADir("/", null);
        ADir current = root;
        // loading the tree
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.startsWith("$ ")) {
                // command:
                if (line.trim().equals("$ ls")) {
                    // directory listing, just ignore it for now...
                } else if (line.trim().equals("$ cd .."))
                    current = current.getParent();
                else {
                    current = current.getDir(line.substring(line.lastIndexOf(" ")).trim());
                }
            } else {
                String[] data = line.trim().split(" ");
                current.addItem(data[1], data[0].trim().equals("dir") ? 0 : Integer.parseInt(data[0]));
            }
        }
        // now find all the dirnames sum for <100k sizes
        int sum = root.findSumOfSizes(100000);
        System.out.println("Part 1: sum of dir sizes under 100000 is " + sum);
        int total = root.getSize();
        int maximum = 70000000;
        int minfree = 30000000;
        System.out.println("Total used space: " + total);
        int toDelete = 0;
        if (total + minfree <= maximum)
            System.out.println("All right, we can proceed as there is enough disk space.");
        else {
            toDelete = total + minfree - maximum;
            System.out.println("We need to delete at least " + toDelete);
        }
        System.out.println("Part 2: sum of calibration values is " + root.smallestLargerThan(toDelete));
    }
}
