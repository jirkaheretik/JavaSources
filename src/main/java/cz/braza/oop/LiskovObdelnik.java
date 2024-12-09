package cz.braza.oop;

public class LiskovObdelnik {
    private int stranaA;
    private int stranaB;
    public LiskovObdelnik(int a, int b) {
        stranaA = a;
        stranaB = b;
    }
    public int spoctiObsah() { return stranaA * stranaB; }
    public int spoctiObvod() { return 2 * (stranaB + stranaA); }

    public int getStranaA() {
        return stranaA;
    }

    public void setStranaA(int stranaA) {
        this.stranaA = stranaA;
    }

    public int getStranaB() {
        return stranaB;
    }

    public void setStranaB(int stranaB) {
        this.stranaB = stranaB;
    }

    @Override
    public String toString() {
        return "Obdelnik{" +
                "stranaA=" + stranaA +
                ", stranaB=" + stranaB +
                '}';
    }

    public static void main(String[] args) {
        LiskovObdelnik x = new LiskovObdelnik(10, 20);
        System.out.println(x.spoctiObsah());
        x = new Ctverec(15);
        System.out.println(x.spoctiObsah());
        x.setStranaB(20);
        System.out.println(x.spoctiObsah());
        System.out.println(x);
    }
}

class Ctverec extends LiskovObdelnik {
    public Ctverec(int a) {
        super(a, a);
    }

    @Override
    public void setStranaA(int stranaA) {
        super.setStranaA(stranaA);
        super.setStranaB(stranaA);
    }
    @Override
    public void setStranaB(int stranaB) {
        super.setStranaA(stranaB);
        super.setStranaB(stranaB);
    }


}
