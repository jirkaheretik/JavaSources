package cz.braza.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NakladniAutoTest {

    @Test
    void testGetter() {
        NakladniAuto tatra = new NakladniAuto();
        assertEquals(0, tatra.getNaklad());
        tatra.naloz(1000);
        assertEquals(1000, tatra.getNaklad());
    }

    @Test
    void testNaloz() {
        NakladniAuto tatra = new NakladniAuto();
        tatra.naloz(2000);
        tatra.naloz(1000); // over limit
        assertEquals(3000, tatra.getNaklad());
    }

    @Test
    void testVyloz() {
        NakladniAuto tatra = new NakladniAuto();
        tatra.naloz(3000);
        tatra.vyloz(1000);
        assertEquals(2000, tatra.getNaklad());
        tatra.vyloz(3000);
        assertEquals(2000, tatra.getNaklad());
    }

    @Test
    void testNosnost() {
        NakladniAuto tatra = new NakladniAuto();
        tatra.naloz(3000);
        tatra.naloz(1000);
        assertEquals(3000, tatra.getNaklad());
        tatra.vyloz(3000);
        assertEquals(0, tatra.getNaklad());
        tatra.naloz(5000);
        assertEquals(0, tatra.getNaklad());
    }
}