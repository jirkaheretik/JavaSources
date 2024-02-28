package cz.braza.educanet;

import java.io.Serializable;

final public class Frac implements Comparable<Frac>, Cloneable, Serializable {
    private final int citatel;
    private final int jmenovatel;
    private int nsd(int a, int b) {
        while (b != 0) { int r = a % b; a = b; b = r; }
        return a;
    }
    public Frac(int a, int b) {
        if (b == 0) throw new ArithmeticException("Jmenovatel nesmi byt roven nule");
        if (a == 0) b = 1;
        if (b < 0)  { a = -a; b = -b; }
        int nsd = nsd(Math.abs(a), b);
        citatel = a / nsd;
        jmenovatel = b / nsd;
    }
    public static Frac fromInt(int a) { return new Frac(a, 1); }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Frac)) return false;
        return citatel == ((Frac) o).citatel && jmenovatel == ((Frac) o).jmenovatel;
    }
    @Override
    public int hashCode() {
        return (17 + 31 * citatel) * 31 + jmenovatel;
    }
    public Frac plus(Frac other) { return new Frac(citatel * other.jmenovatel + other.citatel * jmenovatel, jmenovatel * other.jmenovatel); }
    public Frac minus(Frac other) { return new Frac(citatel * other.jmenovatel - other.citatel * jmenovatel, jmenovatel * other.jmenovatel); }
    public Frac times(Frac other) { return new Frac(citatel * other.citatel, jmenovatel * other.jmenovatel); }
    public Frac divide(Frac other) { return times(other.reciprocal()); }
    public Frac reciprocal() { return new Frac(jmenovatel, citatel); }
    @Override
    public String toString() { return " " + citatel + "/" + jmenovatel; }
    public double toReal() { return (double) citatel / (double) jmenovatel; }

    public static Frac of(int citatel, int jmenovatel) { return new Frac(citatel, jmenovatel); }

    public static void main(String[] args) {
        System.out.println(new Frac(12, 24));
        // 1 / ( 24/351 + 13/8)
        Frac result = (new Frac(24, 351)).plus(new Frac(13, 8)).reciprocal();
        System.out.println(result + " = " + result.toReal());

        System.out.println(new Frac(12, -24));
        result = (new Frac(-24, -351)).plus(new Frac(13, -8)).reciprocal();
        System.out.println(result + " = " + result.toReal());

        Frac half = new Frac(1, 2);
        Frac quarter = new Frac(1, 4);
        Frac multiplication = half.times(quarter);
        System.out.println(multiplication + " = " +  multiplication.toReal());

        Frac f1 = new Frac(3, 4);
        Frac f2 = new Frac(3, 4);
        Frac f3 = Frac.fromInt(3).times(quarter);
        System.out.println("f1:f2 = " + (f1 == f2) + ", equals: " + f1.equals(f2));
        System.out.println("f1:f3 = " + (f1 == f3) + ", equals: " + f1.equals(f3));
        System.out.println("f3:f2 = " + (f3 == f2) + ", equals: " + f3.equals(f2));
        System.out.println((Object)f3);
        System.out.println("----------------");
        System.out.println(Frac.of(-2, -4));
        System.out.println(Frac.of(-2, 4));
        System.out.println(Frac.of(2, -4));
    }

    @Override
    public int compareTo(Frac o) {
        return citatel * o.jmenovatel - o.citatel * jmenovatel;
    }
}
