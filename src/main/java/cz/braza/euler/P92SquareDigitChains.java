package cz.braza.euler;

import java.util.ArrayList;
import java.util.List;

public class P92SquareDigitChains {
    public static final int LIMIT = 10000000;
    public static final int TARGET1 = 1;
    public static final int TARGET2 = 89;

    /**
     * Take a number as parameter and return sum of squares of its digits
     * @param num NUmber to process
     * @return Sum of Squares of digits
     */
    public static int getNext(int num) {
        int result = 0;
        while (num > 0) {
            int digit = num % 10;
            result += digit * digit;
            num /= 10;
        }
        return result;
    }
    public static void main(String[] args) {
        //byte[] targets = new byte[LIMIT + 1]; // where to store endpoints
        //targets[TARGET1] = TARGET1;
        //targets[TARGET2] = TARGET2;
        int count89s = 0; // index 89

        for (int i = 2; i <= LIMIT; i++) {
            int step = i;
            while (step != TARGET1 && step != TARGET2) {
                step = getNext(step);
            }
            if (step == TARGET2)
                count89s ++;
        }
        System.out.println(count89s);

        // main cycle:
        /*
        for (int i = 2; i <= LIMIT; i++) {
            if (targets[i] > 0) continue; // already processed
            List<Integer> midpoints = new ArrayList<>();
            int step = i;
            while (step != TARGET1 && step != TARGET2 && targets[step] == 0) {
                midpoints.add(step);
                step = getNext(step);
            }
            // now we found the endpoint (currently in step or in targets[step]),
            // set it for the whole chain to speed things up
            byte toSet = targets[step] > 0 ? targets[step] : (byte)step;
            for (int point: midpoints) {
                targets[point] = toSet;
                if (toSet == TARGET2)
                    count89s++;
            }
        }
        System.out.println("Preliminary result: " + count89s);
        count89s = 0;
        for (byte i : targets)
            if (i == TARGET2)
                count89s++;
        System.out.println("Final result: " + count89s);

         */
    }
}
