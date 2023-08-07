package cz.braza.educanet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FracTest {

    @Test
    void testEquals() {
        Frac f1 = new Frac(1, 2);
        Frac f2 = new Frac(4, 8);
        assertEquals(f1, f2);
        assertEquals(0.5d, f1.toReal());
    }

    @Test
    void fromInt() {
        assertEquals(5.0, Frac.fromInt(5).toReal());
    }

    @Test
    void plus() {
    }

    @Test
    void minus() {
    }

    @Test
    void times() {
    }

    @Test
    void divide() {
    }

    @Test
    void reciprocal() {
    }

    @Test
    void toReal() {
    }
}