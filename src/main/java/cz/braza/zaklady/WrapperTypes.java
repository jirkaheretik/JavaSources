package cz.braza.zaklady;

class HokusPokus {
    String sVal;
    Integer iVal;
    public HokusPokus() {
        sVal = "X";
        iVal = 11;
    }
    public HokusPokus(String s, int i) {
        sVal = s;
        iVal = i;
    }
    @Override
    public String toString() {
        return String.format("HP (%s) with val %d", sVal, iVal);
    }
    public void setStrVal(String newS) {
        sVal = newS;
    }
    public void setIntVal(int i) {
        iVal = i;
    }
}

public class WrapperTypes {
    public static void main(String[] args) {
        Integer myVal = new Integer(17);
        System.out.println(myVal);
        modifyValue(myVal);
        System.out.println(myVal);

        String mySVal = "My first string value";
        System.out.println(mySVal);
        modifyValue(mySVal);
        System.out.println(mySVal);

        HokusPokus myOVal = new HokusPokus();
        System.out.println(myOVal);
        modifyValue(myOVal);
        System.out.println(myOVal);
        modifyMyValues(myOVal);
        System.out.println(myOVal);
    }

    public static void modifyValue(Integer val) {
        val = new Integer(42);
    }

    public static void modifyValue(String val) {
        val = "new value";
    }

    public static void modifyValue(HokusPokus val) {
        val = new HokusPokus("Karel", 42);
    }

    public static void modifyMyValues(HokusPokus val) {
        val.setIntVal(42);
        val.setStrVal("Karel");
    }
}
