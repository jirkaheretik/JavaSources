package cz.braza.advent;

public class AoC202013AirportShuttle {
    public static void main(String[] args) {
        int timestamp = 1000066;
        int resultValue = 0;
        int lowestWait = Integer.MAX_VALUE;
        String[] buses = "13,x,x,41,x,x,x,37,x,x,x,x,x,659,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,409,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17".split(",");
        int[] lines = {   13,0,0,41,0,0,0,37,0,0,0,0,0,659,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,19,0,0,0,23,0,0,0,0,0,29,0,409,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,17 };

        for (String bus: buses) {
            if ("x".equals(bus)) continue;
            int line = Integer.parseInt(bus);
            int wait = line - (timestamp % line);
            if (wait < lowestWait) {
                lowestWait = wait;
                resultValue = wait * line;
            }
        }
        System.out.println("Result: " + resultValue);
    }
}
