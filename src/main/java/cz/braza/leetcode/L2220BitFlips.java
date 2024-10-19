package cz.braza.leetcode;

public class L2220BitFlips {
    public int[] BITS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
            2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144,
            524288, 1048576, 2097152, 4194304, 8388608, 16777216,
            33554432, 67108864, 134217728, 268435456, 536870912};

    public int minBitFlips(int start, int goal) {
        int xoredValue = start ^ goal;
        int flips = 0;
        for (int bit : BITS)
            if ((bit & xoredValue) > 0) flips++;
        return flips;
    }

}
