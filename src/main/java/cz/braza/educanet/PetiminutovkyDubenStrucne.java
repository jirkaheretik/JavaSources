package cz.braza.educanet;

/**
 * Uloha 4
 * =======
 * - název třídy má začínat malým písmenem
 * - název metody má začínat malým písmenem
 * - název metody má být podle funkce metody, takto nicneříkající název
 * - metoda má brát jako parametr pole čísel
 * - metoda má mít návratový typ a vracet medián (prostřední hodnotu)
 * - nevhodné názvy proměnných - searchMediam by se mohl jmenovat prostredniIndex nebo middleIndex
 * - nevhodné názvy proměnných - numberList by se mohl jmenovat zbytekPoDeleniDvema, ale ještě lépe by to mohl být boolean, např.:
 *   boolean lichyPocet = numbers.length % 2 == 1;
 * - české i anglické identifikátory v jedné třídě není dobrá praktika
 * - funkce vůbec nepočítá medián (neřadí pole, resp. nefunguje s neseřazeným polem)
 * - v případě sudého počtu prvků ani nevypočte hodnotu, ale vypíše dvě z nich
 * - poslední else je zbytečné a nesmyslně komplikuje zápis
 */

class Clovek {
    private String jmeno = "NONAME";
    private int vek = 0;
    private int hendikep = 0;

    public Clovek(String jmeno, int vek, int hendikep) {
        setJmeno(jmeno);
        setVek(vek);
        setHendikep(hendikep);
    }

    @Override
    public String toString() {
        return "Clovek (" + jmeno + ", vek=" + vek + ", hendikep=" + hendikep + ')';
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        if (jmeno != null && !jmeno.isBlank())
            this.jmeno = jmeno;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        if (vek >= 0)
            this.vek = vek;
    }

    public int getHendikep() {
        return hendikep;
    }

    public void setHendikep(int hendikep) {
        if (Math.abs(hendikep) <= 100)
            this.hendikep = hendikep;
    }
}

public class PetiminutovkyDubenStrucne {
    public static long faktorial(int n) {
        return n < 2 ? 1 : n * faktorial(n - 1);
    }

    public static int spoctiSamohlasky(String text) {
        String seznamKPorovnani = "aeiou";
        int pocet = 0;
        for (char c: text.toLowerCase().toCharArray())
            if (seznamKPorovnani.contains("" + c))
                pocet++;
        return pocet;
    }

    public static void main(String[] args) {
        System.out.println(faktorial(13));
        System.out.println(spoctiSamohlasky("Well, hello there, my friend!"));
    }
}
