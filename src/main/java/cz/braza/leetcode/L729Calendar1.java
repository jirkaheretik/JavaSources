package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Next: 731, 732
 * TODO: better lookup (log n instead of O(n))
 */

class Interval {
    private int start;
    private int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public boolean intersects(Interval o) {
        return start < o.end && end > o.start;
    }
}

class MyCalendar {
    private List<Interval> booked = new ArrayList<>();
    public boolean book(int start, int end) {
        Interval newInt = new Interval(start, end);
        for (Interval i: booked)
            if (i.intersects(newInt)) return false;
        booked.add(newInt);
        return true;
    }
}

public class L729Calendar1 {
    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        System.out.println(mc.book(10, 20));
        System.out.println(mc.book(15, 25));
        System.out.println(mc.book(20, 30));
        System.out.println(mc.book(5, 10));
    }
}
