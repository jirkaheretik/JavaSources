package cz.braza.educanet;

import java.util.Scanner;

public class PGM2024OokProgramming {
    public static final String INPUT_HELLO = "Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. Oo! Oo. Oo. Oo? " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. Oo. Oo. Oo! Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo! Oo. Oo! Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo. Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo? Oo! Oo! Oo? Oo! " +
            "Oo? Oo. Oo! Oo. Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo! " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo. Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo. Oo! Oo! Oo! " +
            "Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo. Oo. Oo? Oo. Oo? Oo. Oo. Oo! Oo. ";

    public static final String INPUT_HW = "Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. " +
            "Oo! Oo. Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo? " +
            "Oo! Oo! Oo? Oo! Oo? Oo. Oo. Oo. Oo! Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo. Oo! Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo! Oo. Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. Oo! Oo. " +
            "Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo! Oo? Oo? Oo. Oo. Oo. " +
            "Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo. Oo? Oo! Oo! Oo? Oo! Oo? Oo. Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo. " +
            "Oo? Oo. Oo? Oo. Oo? Oo. Oo? Oo. Oo! Oo. Oo. Oo. Oo. Oo. Oo. Oo. " +
            "Oo! Oo. Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo. " +
            "Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! Oo! " +
            "Oo! Oo. Oo. Oo? Oo. Oo? Oo. Oo. Oo! Oo. ";
    public static final String INPUT_ADD = "Oo. Oo! Oo. Oo! Oo. Oo! Oo. Oo! Oo. Oo! Oo. Oo! Oo? Oo! Oo? Oo. Oo! Oo. Oo? Oo! Oo. Oo. Oo! Oo! Oo. Oo! Oo? Oo! Oo! Oo! ";
    public static final int LIMIT = 30000;

    /*****  Oo ******/

    public static final int INST = 8;
    public static final String I_BANANA = "Oo? Oo? ";
    public static final String I_CYCLE_START = "Oo? Oo! ";
    public static final String I_CYCLE_END = "Oo! Oo? ";
    public static final String I_STORE = "Oo. Oo! ";
    public static final String I_PRINT = "Oo! Oo. ";
    public static final String I_SUB = "Oo! Oo! ";
    public static final String I_ADD = "Oo. Oo. ";
    public static final String I_MEM_ADD = "Oo. Oo? ";
    public static final String I_MEM_SUB = "Oo? Oo. ";

    /***** Ook ****/
    /*
    public static final int INST = 10;
    public static final String I_BANANA = "Ook? Ook? ";
    public static final String I_CYCLE_START = "Ook? Ook! ";
    public static final String I_CYCLE_END = "Ook! Ook? ";
    public static final String I_STORE = "Ook. Ook! ";
    public static final String I_PRINT = "Ook! Ook. ";
    public static final String I_SUB = "Ook! Ook! ";
    public static final String I_ADD = "Ook. Ook. ";
    public static final String I_MEM_ADD = "Ook. Ook? ";
    public static final String I_MEM_SUB = "Ook? Ook. ";

     */
    /******* Brainfuck ************/
/*
    public static final int INST = 1;
    public static final String I_BANANA = " ";
    public static final String I_CYCLE_START = "]";
    public static final String I_CYCLE_END = "[";
    public static final String I_STORE = ",";
    public static final String I_PRINT = ".";
    public static final String I_SUB = "<";
    public static final String I_ADD = ">";
    public static final String I_MEM_ADD = "+";
    public static final String I_MEM_SUB = "-";
    public static final String INPUT_BF_HELLO = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pgm = INPUT_HELLO;
        int instCount = 0;
        int valCount = 0;
        short[] values = new short[LIMIT];
        while (instCount < pgm.length()) {
            String inst = pgm.substring(instCount, instCount + INST);
            //System.out.println(instCount + ". " + inst);
            switch (inst) {
                case I_MEM_ADD:
                    valCount++;
                    if (valCount >= LIMIT) {
                        System.out.println("Memory overflow");
                        valCount = LIMIT - 1;
                    }
                    break;
                case I_MEM_SUB:
                    valCount--;
                    if (valCount < 0) {
                        System.out.println("Memory underflow");
                        valCount = 0;
                    }
                    break;
                case I_ADD:
                    values[valCount]++;
                    break;
                case I_SUB:
                    values[valCount]--;
                    break;
                case I_PRINT:
                    System.out.print(values[valCount] + " ");
                    break;
                case I_STORE:
                    System.out.print("Enter value: ");
                    values[valCount] = Short.parseShort(sc.nextLine());
                    break;
                case I_CYCLE_START:  // start of cycle
                    if (values[valCount] == 0) {
                        // find last start of cycle before current position
                        int pos = instCount - 1;
                        while (pos >= 0 && pos % INST != 0)
                            pos = pgm.lastIndexOf(I_CYCLE_END, pos - 1);
                        if (pos == -1)
                            System.out.println("Unopened cycle");
                        else {
                            instCount = pos;
                        }
                    } // no else, it just continues after the cycle
                    break;
                case I_CYCLE_END: // end of cycle
                    if (values[valCount] != 0) {
                        int pos = instCount + 1;
                        while (pos != -1 && pos % INST != 0)
                            pos = pgm.indexOf(I_CYCLE_START, pos + 1);
                        if (pos == -1)
                            System.out.println("Unclosed cycle");
                        else {
                            instCount = pos;
                        }
                    } // no else, just continue
                    break;
                case I_BANANA: // banana
                    break;
                default:
                    System.out.println("Unknown instruction '" + inst + "' at pos " + instCount);
            }
            instCount += INST;
        }
        System.out.println("\n\nEnd of program reached. Values:");
        for (int i = 0; i < 20; i++)
            System.out.print(values[i] + " ");
        System.out.println();
    }
}
