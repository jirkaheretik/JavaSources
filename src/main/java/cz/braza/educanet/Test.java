package cz.braza.educanet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Test {
    public static void main(String[] args) {
        LocalDateTime datumCas = LocalDateTime.of(LocalDate.of(1939, 9, 1), LocalTime.of(10, 0)); // nastavíme datum 1.9.1939 a 10. hodinu
        System.out.println(datumCas);

        LocalDate zacatek = LocalDate.of(1939, 9, 1);
        LocalDateTime zacatekDne = zacatek.atStartOfDay();
        LocalDateTime danyZacatek = zacatek.atTime(0, 0);
        System.out.println(zacatekDne);
        System.out.println(danyZacatek);
    }
}