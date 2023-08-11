package cz.braza.euler;

import java.time.DayOfWeek;
import java.time.LocalDate;
public class P19CountingSundays {
    public static void main(String[] args) {
        int countSundays = 0;
        LocalDate date;
        for (int year = 1901; year <= 2000; year++)
            for (int month = 1; month <= 12; month++) {
                date = LocalDate.of(year, month, 1);
                if (date.getDayOfWeek() == DayOfWeek.SUNDAY)
                    countSundays++;
            }
        System.out.println(countSundays + " Sundays fell on 1st of the month in the 20th Century.");
    }
}
