package cz.braza.advent.a2024;

import java.util.*;
import java.util.stream.Collectors;

public class Day17ChronospatialComputer {
    public static final int[] D17 = {2,4,1,5,7,5,1,6,4,3,5,5,0,3,3,0};
    public static final long INITA = 47792830;
    public static void main(String[] args) {
        // my code:
        // Part 1:
        run(47792830, 0, 0, D17, true);

        // Part 2 DEBUG:
        /*
        run(3, 0, 0, D17, 0, true);
        run(24, 0, 0, D17, 0, true);
        run(195, 0, 0, D17, 0, true);
        run(1563, 0, 0, D17, 0, true);
        run(12504, 0, 0, D17, 0, true);
        run(100032, 0, 0, D17, 0, true);
        run(800256, 0, 0, D17, 0, true);
        run(6402347, 0, 0, D17, 0, true);
        run(51218782, 0, 0, D17, 0, true);
        run(409750263, 0, 0, D17, 0, true);
        run(3278098532L, 0, 0, D17, 0, true);
        run(26224788258L, 0, 0, D17, 0, true);
        run(209798306068L, 0, 0, D17, 0, true);
        run(1678386448550L, 0, 0, D17, 0, true);
        run(13427091588403L, 0, 0, D17, 0, true);
        run(107416732707226L, 0, 0, D17, 0, true);
         */
        // part 2:
        long regA = 0L;
        for (int length = 1; length <= D17.length; length++) {
            int[] inst = new int[length];
            // fill in instructions:
            for (int i = 0; i < inst.length; i++)
                inst[i] = D17[D17.length - length + i];
            System.out.println("Going in with instructions " + Arrays.toString(inst) + " and regA starting at " + regA);
            while (true) {
                int[] res = run(regA, 0, 0, D17);
                if (Arrays.equals(res, inst)) {
                    System.out.println("Found solution for instructions " + Arrays.toString(inst) + " and reg A: " + regA + ", moving on.");
                    regA = regA << 3;
                    break;
                }
                regA++;
            }
        }
        regA = regA >> 3;
        System.out.println("Now we should have resulting A for part 2: " + regA);

        // run(107416732707226L, 0, 0, D17);
        // outputs itself:
        //run(117440, 0, 0, new int[]{0,3,5,4,3,0});
        /*
        // example
        run(2024, 0, 0, new int[]{0,1,5,4,3,0});
        // example
        run(0, 2024, 43690, new int[]{4,0});
        // example
        run(729, 0, 0, new int[]{0,1,5,4,3,0});
         */
        /*Did not end in like 16mld or so****
        long newA = 2_146_000_000;
        while (true) {
            run(newA++, 0, 0, D17);
        }

         */
    }

    public static int[] run(long A, long B, long C, int[] code) {
        int instPtr = 0;
        List<Integer> output = new ArrayList<>();
        try {
            while (true) {
                switch (code[instPtr]) {
                    case 0: // adv
                        A /= (1 << combo(code[instPtr + 1], A, B, C));
                        break;
                    case 1: // bxl
                        B ^= code[instPtr + 1];
                        break;
                    case 2: // bst
                        B = (int) (combo(code[instPtr + 1], A, B, C) % 8);
                        break;
                    case 3: // jnz
                        if (A != 0) instPtr = code[instPtr + 1] - 2;
                        break;
                    case 4: // bxc
                        B ^= C;
                        break;
                    case 5: // out
                        int value = (int) (combo(code[instPtr + 1], A, B, C) % 8);
                        output.add(value);
                        break;
                    case 6: // bdv
                        B = A / (1 << combo(code[instPtr + 1], A, B, C));
                        break;
                    case 7: // cdv
                        C = A / (1 << combo(code[instPtr + 1], A, B, C));
                        break;
                }
                instPtr += 2;
            }
        } catch (ArrayIndexOutOfBoundsException obe) {}
        return output.stream().mapToInt(i -> i).toArray();
    }

    public static void run(long A, long B, long C, int[] code, boolean print) {
        int[] result = run(A, B, C, code);
        if (print)
            System.out.printf(Arrays.toString(result) + " program ended with A=%d, B=%d, C=%d%n", A, B, C);
    }

    public static long combo(int data, long A, long B, long C) {
        if (data > 6) System.out.println("ERR in processing COMBO with value " + data);
        return switch (data) {
            case 0, 1, 2, 3 -> data;
            case 4 -> A;
            case 5 -> B;
            case 6 -> C;
            default -> -1;
        };
    }
}
