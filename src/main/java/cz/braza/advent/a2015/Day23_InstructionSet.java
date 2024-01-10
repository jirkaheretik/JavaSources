package cz.braza.advent.a2015;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day23_InstructionSet {
    public static void main(String[] args) throws Exception {
        List<String> instructions = Files.readAllLines(Paths.get("/home/jirka/src/java0/aoc15_23.txt"));
        int current = 0;
        int a = 1; // initial state 0 for Part 1, 1 for Part 2
        int b = 0;
        int count = 0;
        while (current < instructions.size()) {
            String inst = instructions.get(current);
            String code = inst.substring(0, 3);
            count++;
            int jmp = 1;
            switch (code) {
                case "hlf":
                    if (a % 2 == 1) System.out.println("A has an odd value " + a);
                    a /= 2;
                    break;
                case "tpl":
                    a *= 3;
                    break;
                case "inc":
                    if (inst.charAt(4) == 'b') b++;
                    else a++;
                    break;
                case "jmp":
                    jmp = Integer.parseInt(inst.substring(4));
                    break;
                case "jie":
                    if (a % 2 == 0)
                        jmp = Integer.parseInt(inst.substring(7));
                    break;
                case "jio":
                    if (a == 1)
                        jmp = Integer.parseInt(inst.substring(7));
                    break;
                default:
                    System.out.println("Unknown instruction " + code);
            }
            current += jmp;
            // if (count % 1000 == 0) System.out.print(".");
        }
        System.out.println("Program ended. Register b = " + b + ", a = " + a + ", run " + count + " instructions.");
    }
}
