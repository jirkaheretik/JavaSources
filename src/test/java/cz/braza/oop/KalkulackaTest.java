package cz.braza.oop;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class KalkulackaTest {

    @Test
    void secti() {
        Kalkulacka kalkulacka = new Kalkulacka(3, 5);
        assertEquals(8, kalkulacka.secti(), 0.00001);

        kalkulacka = new Kalkulacka(-13, -29);
        assertEquals(-42, kalkulacka.secti(), 0.00001);
    }

    @Test
    void sectiStaticky() {
        assertEquals(3, Kalkulacka.secti(1, 2));
    }

    @Test
    void odecti() {
        Kalkulacka kalkulacka = new Kalkulacka(3, 5);
        assertEquals(-2, kalkulacka.odecti(), 0.00001);

        kalkulacka = new Kalkulacka(-13, -29);
        assertEquals(16, kalkulacka.odecti(), 0.00001);

    }

    @Test
    void vynasob() {
        Kalkulacka kalkulacka = new Kalkulacka(3, 5);
        assertEquals(15, kalkulacka.vynasob(), 0.00001);

        kalkulacka = new Kalkulacka(-13, -9);
        assertEquals(117, kalkulacka.vynasob(), 0.00001);
    }

    @Test
    void vydel() {
        Kalkulacka kalkulacka = new Kalkulacka(15, 3);
        assertEquals(5, kalkulacka.vydel(), 0.00001);

        kalkulacka = new Kalkulacka(-26, -13);
        assertEquals(2, kalkulacka.vydel(), 0.00001);

        final Kalkulacka k2 = new Kalkulacka(42, 0);
        assertThrows(ArithmeticException.class, () -> k2.vydel());
    }

    @Test
    void vydelCelociselne() {
        assertEquals(4, Kalkulacka.vydelCelociselne(24, 6));
        assertEquals(7, Kalkulacka.vydelCelociselne((int) 15.46, (int) 2.98));
        assertThrows(ArithmeticException.class, () -> Kalkulacka.vydelCelociselne(42, 0));

    }
}