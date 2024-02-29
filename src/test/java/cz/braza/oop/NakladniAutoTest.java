package cz.braza.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NakladniAutoTest {

    @Test
    void testNaloz() {
        NakladniAuto tatra = new NakladniAuto();
        tatra.naloz(2000);
        tatra.naloz(1000); // over limit
        Assert.assertEquals(3000, tatra.getNaklad());
    }

    @Test
    void testVyloz() {
        NakladniAuto tatra = new NakladniAuto();
        tatra.naloz(3000);
        tatra.vyloz(1000);
        Assert.assertEquals(2000, tatra.getNaklad());
        tatra.vyloz(3000);
        Assert.assertEquals(2000, tatra.getNaklad());
    }

    @Test
    void testGetter() {
        NakladniAuto tatra = new NakladniAuto();
        Assert.assertEquals(0, tatra.getNaklad());
        tatra.naloz(1000);
        Assert.assertEquals(1000, tatra.getNaklad());
    }
}